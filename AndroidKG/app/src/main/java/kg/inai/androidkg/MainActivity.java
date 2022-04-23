package kg.inai.androidkg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Script;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity
{

    TextView workingsTV;
    TextView resultsTV;

    String workings = "";
    String formula = "";
    String tempformula = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews()
    {
        workingsTV = (TextView) findViewById(R.id.workingsTextView);
        resultsTV = (TextView) findViewById(R.id.resultTextView);

    }

    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }

    public void equalsOnClick(View view) {

        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowOf();

        try {
            result = (double)engine.eval(formula);
        }catch (Exception e)
        {
            Toast.makeText(this, "Invalid  input", Toast.LENGTH_SHORT).show();

        }
       if (result !=null)
           resultsTV.setText(String.valueOf(result.doubleValue()));

    }
    private void checkForPowOf()
    {
        ArrayList<Integer> indexOfPow = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++)
        {

            if(workings.charAt(i) == '^')
                indexOfPow.add(i);
        }

        formula = workings;
        tempformula = workings;

        for (Integer index: indexOfPow){
            changeFormula(index);
        }
        formula = tempformula;

    }

    private void changeFormula(Integer index){

        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i < workings.length() ; i++) {

            if(isNummeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;

        }
        for (int i = index - 1; i > 0 ; i--) {


            if(isNummeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";


        tempformula = tempformula.replace(original,changed);


    }
    private boolean isNummeric(char c)
    {
        if((c <='9' && c >='0')|| c== '.')
            return true;

        return  false;
    }

    public void clearOnClick(View view) {
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftQuotes = true;
    }

    boolean leftQuotes = true;


    public void quotesOnClick(View view)
    {
        if(leftQuotes) {
            setWorkings("(");
            leftQuotes = false;

        }else  {
            setWorkings(")");
            leftQuotes = true;
        }
    }


    public void powOnClick(View view) {
        setWorkings("^");
    }

    public void divisionOnClick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void multiplyOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void nullOnClick(View view) {
        setWorkings("0");
    }



    public void dotOnClick(View view) {
        setWorkings(".");
    }
}