package com.example.tcc;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tcc.permissao.Permissao;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

// may be empty

public class MainActivity extends AppCompatActivity {


    private String[] permissoesNecessarias = new String[]{Manifest.permission.CAMERA};



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for ( int permissaoResultado : grantResults ){
            if ( permissaoResultado == PackageManager.PERMISSION_DENIED ){
                alertaValidacaoPermissao();
            }
        }

    }

    private void alertaValidacaoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private CameraDevice mCameraDevice;
    @TargetApi(LOLLIPOP)
    private CameraDevice.StateCallback mCameraDeviceStateCallback
            = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice cameraDevice) {

            Toast.makeText(getApplicationContext(), "Câmera em uso", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onDisconnected(CameraDevice cameraDevice) {

        }

        @Override
        public void onError(CameraDevice cameraDevice, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        alertaValidacaoPermissao();

        startService(new Intent(getBaseContext(), TestService.class));
        //startService(new Intent(getBaseContext(), CameraCheck.class));
        notify();

        //Validar permissões
        Permissao.validarPermissoes(permissoesNecessarias, this, 1);

    }
}
