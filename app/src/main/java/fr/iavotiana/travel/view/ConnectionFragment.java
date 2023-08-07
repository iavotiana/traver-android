package fr.iavotiana.travel.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.iavotiana.travel.R;
import fr.iavotiana.travel.model.UserModel;
import fr.iavotiana.travel.model.UserResponse;
import fr.iavotiana.travel.retrofit.IMyApi;
import fr.iavotiana.travel.retrofit.RetrofitClient;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Retrofit;


public class ConnectionFragment extends Fragment {


    private UserResponse user;
    EditText txtEmail ,txtPassword;
    Button btnLogin;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyApi iMyApi;

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connection, container, false);

        //Initialisation Service
        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyApi = retrofitClient.create(IMyApi.class);

        //view
        txtEmail = view.findViewById(R.id.txtEmail);
        txtPassword = view.findViewById(R.id.txtPassword);

        btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(txtEmail.getText().toString(), txtPassword.getText().toString());
            }
        });

        Button inscriptionButton = view.findViewById(R.id.buttonInscription);
        inscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InscriptionFragment inscriptionFragment = new InscriptionFragment();
                switchFragment(inscriptionFragment);
            }
        });



        return view;
    }
    private void loginUser(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            if (isAdded()) {
                Toast.makeText(getContext(), "Email cannot be empty", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (TextUtils.isEmpty(password)) {
            if (isAdded()) {
                Toast.makeText(getContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        compositeDisposable.add(iMyApi.loginUser(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        apiResponse -> {
                            UserModel user = apiResponse.getUserModel();
                            String token = apiResponse.getToken();
                            String toastMessage =" Bonjour  " + user.getNom() ;
                            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_LONG).show();

                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", token);
                            editor.apply();

                        },
                        throwable -> {
                            if (throwable instanceof HttpException) {
                                try {
                                    ResponseBody errorBody = ((HttpException) throwable).response().errorBody();
                                    if (errorBody != null) {
                                        JSONObject jsonObject = new JSONObject(errorBody.string());
                                        String errorMessage = jsonObject.optString("message");
                                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "Login failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "Login failed: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }
    public void switchFragment(Fragment secondFragment){
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, secondFragment);
        transaction.addToBackStack(null); // Add to back stack for back navigation
        transaction.commit();
    }
}