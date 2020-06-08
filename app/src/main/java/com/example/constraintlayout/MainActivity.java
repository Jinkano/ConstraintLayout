package com.example.constraintlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // VARIABLES PARA LAS VIEWS
    Switch swVerLayouts;
    Spinner spnLayouts;
    RadioButton rbReloj, rbCalendario;
    ImageButton ibReloj, ibCalendario;
    CheckBox cbHora, cbFecha;
    TextView tvHora, tvFecha;
    Button btnInfo, btnSalir;

    // INTENT PARA LANZAR LAS OTRAS ACTIVIDADES
    Intent intent = new Intent();

    // VARIABLES PARA EL FORMATO DE LA FECHA Y LA HORA
    String strCero = "0";
    String strBarra = "/";
    //String strPuntos = ":";
    String strHora, strFecha;

    // CLASE Y VARIABLES PARA OBTENER LA FECHA Y LA HORA DEL SISTEMA
    Calendar calendar = Calendar.getInstance();
    int intDia = calendar.get(Calendar.DAY_OF_MONTH);
    int intMes = calendar.get(Calendar.MONTH);
    int intAnio = calendar.get(Calendar.YEAR);
    int intMinuto = calendar.get(Calendar.MINUTE);
    int intHora = calendar.get(Calendar.HOUR_OF_DAY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ENLAZAMOS LAS VIEW CON LAS VARIABLES
        swVerLayouts = findViewById(R.id.idSwVerLayouts);
        spnLayouts = findViewById(R.id.idSpnLayouts);
        rbReloj = findViewById(R.id.idRbReloj);
        rbCalendario = findViewById(R.id.idRbCalendario);
        ibReloj = findViewById(R.id.idIbReloj);
        ibCalendario = findViewById(R.id.idIbCalendario);
        cbHora = findViewById(R.id.idCbHora);
        cbFecha = findViewById(R.id.idCbFecha);
        tvHora = findViewById(R.id.idTvHora);
        tvFecha = findViewById(R.id.idTvFecha);
        btnInfo = findViewById(R.id.idBtnInfo);
        btnSalir = findViewById(R.id.idBtnSalir);

        // INICIAMOS CON EL SPINNER DESACTIVADO
        spnLayouts.setEnabled(false);

        /*
        * MÉTODO setOnCheckedChangeListener DEL SWITCH swVerLayouts
        * ---------------------------------------------------------
        * +) USAMOS IF PARA COMPROBAR SI EL SWITCH ESTA ACTIVADO/DESACTIVADO:
        *   +) SI ESTÁ ACTIVADO:
        *       -) MOSTRAMOS UN TOAST Y ACTIVAMOS EL SPINNER.
        *   +) SI ESTÁ DESACTIVADO:
        *       -) MOSTRAMOS UN TOAST Y DESACTIVAMOS EL SPINNER.
        * */
        swVerLayouts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (swVerLayouts.isChecked()) {
                    spnLayouts.setEnabled(true);
                    Toast.makeText(MainActivity.this, "Switch Activado", Toast.LENGTH_SHORT).show();
                } else {
                    spnLayouts.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Switch Desactivado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        * -) LLENAMOS EL SPINNER CON LA LISTA CREADA EN EL ARCHIVO XML USANDO ARRAYADAPTER.
        * -) LE DAMOS FORMATO CON EL SPINNER PERSONALIZADO.
        * */
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_layout, R.layout.spinner_personalizado);
        spnLayouts.setAdapter(adapter);

        /*
        * METODO setOnItemSelectedListener SPINNER spnLayouts
        * ---------------------------------------------------
        * + USAMOS SWITCH PARA LANZAR UNA ACTIVIDAD SEGÚN EL ITEM SELECCIONADO DEL SPINNER
        *   + CASE:
        *       - LANZAMOS LA OTRA ACTIVIDAD CON EL INTENT.
        *       - MOSTRAMOS UN TOAST.
        * */
        spnLayouts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        intent.setClass(getApplicationContext(), FramelayoutActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Opción : " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        intent.setClass(getApplicationContext(), LinearHorizontalActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Opción : " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        intent.setClass(getApplicationContext(), LinearVerticalActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Opción : " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
                        break;

                    case 4:
                        intent.setClass(getApplicationContext(), RelativelayoutActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Opción : " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
                        break;

                    case 5:
                        intent.setClass(getApplicationContext(), GridlayoutActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Opción : " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
                        break;

                    case 6:
                        intent.setClass(getApplicationContext(), TablelayoutActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "Opción : " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        /*
        * MÉTODO setOnClickListener DEL RADIOBUTTON rbReloj
        * -------------------------------------------------
        * - LLAMAMOS AL MÉTODO pvHora.
        * - DESMARCAMOS EL CHECKBOX cbHora.
        * - MOSTRAMOS UN TOAST.
        * + IF: COMPROBAMOS SI RADIOBUTTON rbCalendario ESTA MARCADO:
        *   - DESMARCAMOS EL RADIOBUTTON rbCalendario.
        */
        rbReloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvHora();
                cbHora.setChecked(false);
                Toast.makeText(MainActivity.this, "Reloj : RadioButton" ,Toast.LENGTH_SHORT).show();
                if (rbCalendario.isChecked()){rbCalendario.setChecked(false);}
            }
        });

        /*
        * MÉTODO setOnClickListener DEL RADIOBUTTON rbCalendario
        * ------------------------------------------------------
        * - LLAMAMOS AL MÉTODO pvFecha.
        * - DESMARCAMOS EL CHECKBOX cbFecha.
        * - MOSTRAMOS UN TOAST.
        * + IF: COMPROBAMOS SI RADIOBUTTON rbReloj ESTA MARCADO:
        *   - DESMARCAMOS EL RADIOBUTTON rbReloj.
        * */
        rbCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvFecha();
                cbFecha.setChecked(false);
                Toast.makeText(MainActivity.this, "Calendario : RadioButton" ,Toast.LENGTH_SHORT).show();
                if (rbReloj.isChecked()){rbReloj.setChecked(false);}
            }
        });

        /*
        * MÉTODO
        * --------
        * */
        ibReloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamar al picker
                pvHora();
                //desmarcar el radiogroup rgPickers.clearCheck();
                if (rbReloj.isChecked()){rbReloj.setChecked(false);}
                //desmarcar los check
                if (cbHora.isChecked()){cbHora.setChecked(false);}
                Toast.makeText(MainActivity.this, "Reloj : ImageButton" ,Toast.LENGTH_SHORT).show();
            }
        });

        /*
        * MÉTODO
        * ------
        * */
        ibCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamar al picker
                pvFecha();
                //desmarcar el radiogroupb rgPickers.clearCheck();
                if (rbCalendario.isChecked()){rbCalendario.setChecked(false);}
                //desmarcar los check
                if (cbFecha.isChecked()){cbFecha.setChecked(false);}
                Toast.makeText(MainActivity.this, "Calendario : ImageButton" ,Toast.LENGTH_SHORT).show();
            }
        });

        /*
        * MÉTODO setOnCheckedChangeListener DEL CHECKBOX cbHora
        * -----------------------------------------------------
        * +
        * */
        cbHora.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((isChecked) && (strHora == null)){
                    tvHora.setText(R.string.txt_select_hora);
                    cbHora.setChecked(false);
                } else {
                    if (isChecked){
                        tvHora.setText(strHora);
                        Toast.makeText(MainActivity.this, "Mostrando la hora.", Toast.LENGTH_SHORT).show();
                    } else {
                        tvHora.setText(R.string.txt_00_00);
                        Toast.makeText(MainActivity.this, "Hora desactivada.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*
        * MÉTODO setOnCheckedChangeListener DEL CHECKBOX cbFecha
        * ------------------------------------------------------
        * +
        * */
        cbFecha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((isChecked) && (strFecha == null)){
                    tvFecha.setText(R.string.txt_select_fecha);
                    cbFecha.setChecked(false);
                } else {
                    if (isChecked){
                        tvFecha.setText(strFecha);
                        Toast.makeText(MainActivity.this, "Mostrando la fecha.", Toast.LENGTH_SHORT).show();
                    } else {
                        tvFecha.setText(R.string.txt_05_11_1979);
                        Toast.makeText(MainActivity.this, "Fecha desactivada.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*
        * MÉTODO setOnClickListener DEL BUTTON btnInfo
        * --------------------------------------------
        * - MOSTRAMOS INFORMACIÓN ACERCA DEL APP.
        * */
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mostrar informacion", Toast.LENGTH_SHORT).show();
                pvAdInfo();
            }
        });

        /*
        * MÉTODO setOnClickListener DEL BUTTON btnSalir
        * ---------------------------------------------
        * - CERRAMOS LA APP USANDO finish.
        * */
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /*
    * TODO : ALERTDIALOG
    * ------------------
    * */
    public void pvAdInfo(){
        AlertDialog.Builder adbInfo = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.activity_info, null);
        //TextView tvInfo = view.findViewById(R.id.idTvInfo);
        adbInfo.setView(view).setTitle("Info. App").create().show();
    }


    // MÉTODO PARA LA FECHA
    public void pvFecha(){
        DatePickerDialog dpdFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                //Formato para el Dia/Mes si es menor a 10.
                String strFormatoDia = (dayOfMonth < 10)? strCero + dayOfMonth: String.valueOf(dayOfMonth);
                String strFormatoMes = (month + 1 < 10)? strCero + (month + 1): String.valueOf(month + 1);

                //Mostramos la fecha con el formato
                strFecha = (strFormatoDia + strBarra + strFormatoMes + strBarra + year);

                ////
                @SuppressLint("SimpleDateFormat")
                SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date dtFecha = sdfFecha.parse(strFecha);
                    assert dtFecha != null;
                    calendar.setTime(dtFecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String strDia = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
                String strMes = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
                strFecha = strDia + ", " + dayOfMonth + " de " + strMes + " del " + year;
                ////
            }
        },intAnio, intMes, intDia);

        //Mostramos la Fecha
        dpdFecha.show();
    }

    // MÉTODO PARA LA HORA
    public void pvHora(){
        TimePickerDialog tpdHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //Formato para el Minuto/Hora si es menor a 10.
                String strFormatoMinuto = (minute < 10)? strCero + minute: String.valueOf(minute);
                String strFormatoHora = (hourOfDay < 10)? strCero + (hourOfDay): String.valueOf(hourOfDay);

                /*/Obtener valor AM/PM
                String strAmPm;
                if (hourOfDay < 12){
                    strAmPm = "a.m.";
                }else{
                    strAmPm = "p.m.";
                }*/

                //Mostramos la hora con el formato
                strHora = ("Son las " + strFormatoHora + " horas y " + strFormatoMinuto + " minutos");
            }
        },intHora, intMinuto, true);
        tpdHora.show();
    }
}