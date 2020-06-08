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
        * TODO : MÉTODO setOnCheckedChangeListener DEL SWITCH swVerLayouts
        * ----------------------------------------------------------------
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
        * TODO : ARRAYADAPTER
        * -) LLENAMOS EL SPINNER CON LA LISTA CREADA EN EL ARCHIVO XML USANDO ARRAYADAPTER.
        * -) LE DAMOS FORMATO CON EL SPINNER PERSONALIZADO.
        * */
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_layout, R.layout.spinner_personalizado);
        spnLayouts.setAdapter(adapter);

        /*
        * TODO : METODO setOnItemSelectedListener SPINNER spnLayouts
        * ----------------------------------------------------------
        * + SWITCH: PARA LANZAR UNA ACTIVIDAD SEGÚN EL ITEM SELECCIONADO DEL SPINNER
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
        * TODO : MÉTODO setOnClickListener DEL RADIOBUTTON rbReloj
        * --------------------------------------------------------
        * - LLAMAMOS AL MÉTODO pvHora.
        * - DESMARCAMOS EL CHECKBOX cbHora.
        * - MOSTRAMOS UN TOAST.
        * + IF: COMPROBAMOS SI RADIOBUTTON rbCalendario ESTA MARCADO.
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
        * TODO : MÉTODO setOnClickListener DEL RADIOBUTTON rbCalendario
        * -------------------------------------------------------------
        * - LLAMAMOS AL MÉTODO pvFecha.
        * - DESMARCAMOS EL CHECKBOX cbFecha.
        * - MOSTRAMOS UN TOAST.
        * + IF: COMPROBAMOS SI RADIOBUTTON rbReloj ESTA MARCADO:
        *       - DESMARCAMOS EL RADIOBUTTON rbReloj.
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
        * TODO : MÉTODO setOnClickListener DEL IMAGEBUTTON ibReloj
        * --------------------------------------------------------
        * - LLAMAMOS AL MÉTODO DEL PICKER pvHora.
        * + IF: COMPROBAMOS SI EL RADIOBUTTON rbReloj ESTÁ MARCADO.
        *       - DESMARCAMOS RADIOBUTTON rbReloj.
        * + IF: COMPROBAMOS SI CHECKBOX cbHora ESTÁ MARCADO.
        *       - DESMARCAMOS CHECKBOX cbHora.
        * - MOSTRAMOS UN TOAST.
        * */
        ibReloj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvHora();
                if (rbReloj.isChecked()){rbReloj.setChecked(false);}
                if (cbHora.isChecked()){cbHora.setChecked(false);}
                Toast.makeText(MainActivity.this, "Reloj : ImageButton" ,Toast.LENGTH_SHORT).show();
            }
        });

        /*
        * TODO : MÉTODO setOnClickListener DEL IMAGEBUTTON ibCalendario
        * -------------------------------------------------------------
        * - LLAMAMOS AL MÉTODO DEL PICKER pvFecha.
        * + IF: COMPROBAMOS SI EL RADIOBUTTON rbCalendario ESTÁ MARCADO.
        *       - DESMARCAMOS RADIOBUTTON rbCalendario.
        * + IF: COMPROBAMOS SI CHECKBOX cbFecha ESTÁ MARCADO.
        *       - DESMARCAMOS CHECKBOX cbFecha.
        * - MOSTRAMOS UN TOAST.
        * */
        ibCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvFecha();
                if (rbCalendario.isChecked()){rbCalendario.setChecked(false);}
                if (cbFecha.isChecked()){cbFecha.setChecked(false);}
                Toast.makeText(MainActivity.this, "Calendario : ImageButton" ,Toast.LENGTH_SHORT).show();
            }
        });

        /*
        * TODO : MÉTODO setOnCheckedChangeListener DEL CHECKBOX cbHora
        * ------------------------------------------------------------
        * + IF: COMPROBAMOS SI cbHora ESTÁ MARCADO Y LA VARIABLE strHora ESTA VACIA.
        *       - MOSTRAMOS UN TEXTO EN EL TEXTVIEW tvHora.
        *       - DESMARCAMOS cbHora.
        * + ELSE:
        *       + IF: SI ESTÁ MARCADO
        *           - MOSTRAMOS LA HORA EN EL tvHora.
        *           - MOSTRAMOS UN TOAST.
        *       + ELSE:
        *           - BORRAMOS LA HORA MOSTRADA EN EL tvHora.
        *           - MOSTRAMOS UN TOAST.
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
        * TODO : MÉTODO setOnCheckedChangeListener DEL CHECKBOX cbFecha
        * -------------------------------------------------------------
        * + IF: COMPROBAMOS SI cbFecha ESTÁ MARCADO Y LA VARIABLE strFecha ESTA VACIA.
        *       - MOSTRAMOS UN TEXTO EN EL TEXTVIEW tvFecha.
        *       - DESMARCAMOS cbFecha.
        * + ELSE:
        *       + IF: SI ESTÁ MARCADO
        *           - MOSTRAMOS LA FECHA EN EL tvFecha.
        *           - MOSTRAMOS UN TOAST.
        *       + ELSE:
        *           - BORRAMOS LA FECHA MOSTRADA EN EL tvFecha.
        *           - MOSTRAMOS UN TOAST.
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
        * TODO : MÉTODO setOnClickListener DEL BUTTON btnInfo
        * ---------------------------------------------------
        * - LLAMAMOS AL MÉTODO pvAdInfo.
        * */
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvAdInfo();
            }
        });

        /*
        * TODO : MÉTODO setOnClickListener DEL BUTTON btnSalir
        * ----------------------------------------------------
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
    * TODO : ALERTDIALOG pvAdInfo
    * ---------------------------
    * - CREAMOS UN OBJETO adbInfo DE LA CLASE ALERTDIALOG.
    * - CREAMOS UN OBJETO layoutInflater DE LA CLASE LAYOUTINFLATER.
    * - CREAMOS U  OBJETO DE LA CLASE VIEW Y LE PASAMOS EL LAYOUT activity_info.
    * - LE DAMOS UN TÍTULO, LO CREAMOS Y LO MOSTRAMOS.
    * */
    public void pvAdInfo(){
        AlertDialog.Builder adbInfo = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams")
        View view = layoutInflater.inflate(R.layout.activity_info, null);
        adbInfo.setView(view).setTitle("Info. App").create().show();
    }


    /*
    * TODO : MÉTODO PARA LA FECHA
    * ---------------------------
    * + CREAMOS UN OBJETO DE LA CLASE DATEPICKERDIALOG:
    *   - LE DAMOS FORMATO AL DÍA Y AL MES SI ES MENOR A 10.
    *   - LE ASIGNAMOS LA FECHA SELECCIONADA A LA VARIABLE strFecha.
    *   + CREAMOS UN OBJETO DE LA CLASE SIMPLEDATEFORMAT
    *       - CREAMMOS UNA VARIABLE TIPO DATE Y LE ASIGNAMOS EL VALOR DE LA VARIABLE strFecha
    *       - LE PASAMOS LA FECHA A CALENDAR.
    *   - LE PASAMOS A LAS VARIABLES strDia Y strMes EL DÍA Y EL MES OBTENIDOS DE CALENDAR.
    *   - CONCAQUETAMOS LA CADENA Y SE LO PASAMOS A LA VARIABLE strFecha.
    * - MOSTRAMOS dpdFecha.
    * */
    public void pvFecha(){
        DatePickerDialog dpdFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String strFormatoDia = (dayOfMonth < 10)? strCero + dayOfMonth: String.valueOf(dayOfMonth);
                String strFormatoMes = (month + 1 < 10)? strCero + (month + 1): String.valueOf(month + 1);
                strFecha = (strFormatoDia + strBarra + strFormatoMes + strBarra + year);
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
            }
        },intAnio, intMes, intDia);
        dpdFecha.show();
    }

    /*
    * TODO : MÉTODO PARA LA HORA
    * --------------------------
    * + CREAMOS UN OBJETO DE LA CLASE TIMERPICKERDIALOG:
    *   - LE DAMOS FORMATO ALA HORA Y A LOS MINUTOS SI ES MENOR A 10.
    *   - CONCAQUETAMOS LA CADENA Y SE LO PASAMOS A LA VARIABLE strHora.
    * - MOSTRAMOS tpdHora.
    * */
    public void pvHora(){
        TimePickerDialog tpdHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String strFormatoMinuto = (minute < 10)? strCero + minute: String.valueOf(minute);
                String strFormatoHora = (hourOfDay < 10)? strCero + (hourOfDay): String.valueOf(hourOfDay);
                /*/Obtener valor AM/PM
                String strAmPm;
                if (hourOfDay < 12){
                    strAmPm = "a.m.";
                }else{
                    strAmPm = "p.m.";
                }*/
                strHora = ("Son las " + strFormatoHora + " horas y " + strFormatoMinuto + " minutos");
            }
        },intHora, intMinuto, true);
        tpdHora.show();
    }
}