package com.pratica.calculadoradaora;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult, tvConta;
    MaterialButton btnAC, btnC, btnAbrirP, btnFecharP, btnPonto;
    MaterialButton btnDividir, btnMultiplicar, btnSubtrair, btnSomar, btnIgual;
    MaterialButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tv_result);
        tvConta = findViewById(R.id.tv_conta);

        // tvConta.setText("");

        atribuirId(btn0, R.id.btn_0);
        atribuirId(btn1, R.id.btn_1);
        atribuirId(btn2, R.id.btn_2);
        atribuirId(btn3, R.id.btn_3);
        atribuirId(btn4, R.id.btn_4);
        atribuirId(btn5, R.id.btn_5);
        atribuirId(btn6, R.id.btn_6);
        atribuirId(btn7, R.id.btn_7);
        atribuirId(btn8, R.id.btn_8);
        atribuirId(btn9, R.id.btn_9);
        atribuirId(btnPonto, R.id.btn_Ponto);
        atribuirId(btnIgual, R.id.btn_Igual);
        atribuirId(btnAC, R.id.btn_AC);
        atribuirId(btnC, R.id.btn_C);
        atribuirId(btnAbrirP, R.id.btn_AbreP);
        atribuirId(btnFecharP, R.id.btn_FechaP);
        atribuirId(btnSomar, R.id.btn_Somar);
        atribuirId(btnSubtrair, R.id.btn_Subtrair);
        atribuirId(btnDividir, R.id.btn_Dividir);
        atribuirId(btnMultiplicar, R.id.btn_Multiplicar);

    }

    void atribuirId(MaterialButton button, int id) {
        button = findViewById(id);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();

        String contaPorCalcular = tvConta.getText().toString();

        //Apaga todo o texto
        if (btnText.equals("AC")) {
            tvConta.setText("");
            tvResult.setText("0");
            return;
        }

        //Apagar o ultimo nr
       if (btnText.equals("C")) {
            try {
                contaPorCalcular = contaPorCalcular.substring(0, contaPorCalcular.length() - 1);
                tvConta.setText(contaPorCalcular);
            } catch (Exception e) {
               // if (btnText.equals("C")) {
                    tvConta.setText("");
                    tvResult.setText("0");
                    //contaPorCalcular = contaPorCalcular + btnText;
                    return;
               // }
            }
        } else {
            contaPorCalcular = contaPorCalcular + btnText;
        }

      //  contaPorCalcular = contaPorCalcular + btnText;
        // Trata o botão de igual
        if (btnText.equals("=")) {
            tvConta.setText(tvResult.getText());
            return;
        }

        tvConta.setText(contaPorCalcular);
        String finalResult = getResult(contaPorCalcular);

        if (!finalResult.equals("Erro")) {
            tvResult.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();

            String finalResult = (context.evaluateString(scriptable, data, "Javascript", 1, null).toString());
            if (finalResult.endsWith(".0")){
                finalResult  = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e) {
            return "Erro";
        }

    }
}


