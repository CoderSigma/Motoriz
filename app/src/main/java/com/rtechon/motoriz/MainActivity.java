package com.rtechon.motoriz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "MainActivity";

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView;
    private SignInButton googleSignInButton;
    private com.facebook.login.widget.LoginButton facebookLoginButton;

    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth mAuth;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Facebook SDK
        FacebookSdk.setApplicationId("2043879212769200");
        FacebookSdk.setClientToken("96e72f6f3da2fd2eff6690c1feaa7435");
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this.getApplication());

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Facebook Callback Manager
        callbackManager = CallbackManager.Factory.create();

        // Initialize Views
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);
        googleSignInButton = findViewById(R.id.sign_in_button);
        facebookLoginButton = findViewById(R.id.facebook_login_button);

        // Set Facebook permissions
        facebookLoginButton.setPermissions(Arrays.asList("email", "public_profile"));

        // Facebook Login Callback
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Facebook login canceled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "Facebook login failed", error);
                Toast.makeText(MainActivity.this, "Facebook login failed: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Google Sign-In configuration
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Google Sign-In Button Click
        googleSignInButton.setOnClickListener(v -> signInWithGoogle());

        // Register Page Redirection
        registerTextView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegistrationActivity.class)));

        // Custom Username/Password Login
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            loginUser(username, password);
        });
    }

    private void loginUser(String username, String password) {
        String url = "http://egone.cn/api_appLogin.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        String status = jsonResponse.getString("status");
                        String message = jsonResponse.getString("message");

                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                        if (status.equals("success")) {
                            JSONObject userObj = jsonResponse.getJSONObject("user");
                            String userId = userObj.getString("user_id");
                            String usernameResponse = userObj.getString("username");
                            String email = userObj.getString("email");

                            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                            intent.putExtra("userId", userId);
                            intent.putExtra("username", usernameResponse);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        }

                    } catch (JSONException e) {
                        Log.e(TAG, "Response error: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "Response error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    Log.e(TAG, "Connection error: " + error.getMessage());
                    Toast.makeText(MainActivity.this, "Connection error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        sendUserToServer(user);  // Send Google user info to server
                    } else {
                        Toast.makeText(MainActivity.this, "Google Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendUserToServer(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();

            // Send Google user info to the PHP API
            String url = "http://egone.cn/google_api_login.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String status = jsonResponse.getString("status");
                            String message = jsonResponse.getString("message");

                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

                            if (status.equals("success")) {
                                openDashboard(user);
                            }
                        } catch (JSONException e) {
                            Log.e(TAG, "Response error: " + e.getMessage());
                            Toast.makeText(MainActivity.this, "Response error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        Log.e(TAG, "Connection error: " + error.getMessage());
                        Toast.makeText(MainActivity.this, "Connection error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("email", email);
                    return params;
                }
            };

            // Add the request to the Volley request queue
            Volley.newRequestQueue(this).add(stringRequest);
        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        openDashboard(user);
                    } else {
                        Toast.makeText(MainActivity.this, "Facebook Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openDashboard(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            intent.putExtra("userId", user.getUid());
            intent.putExtra("username", user.getDisplayName());
            intent.putExtra("email", user.getEmail());
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Facebook callback
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // Google callback
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Google sign in failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
