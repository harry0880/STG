package hsquare.com.stg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import hsquare.com.stg.getset.Disease_Fragments;
import hsquare.com.stg.getset.Getset_ListView;
import hsquare.com.stg.utils.DbHandler;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class displayHTML extends AppCompatActivity {

    TextView html;
    DbHandler dbh;
    Disease_Fragments getset;

    String html_str="<h1>ACUTE FEVER</h1>\n" +
            "The overall mean oral temperature for healthy adult individuals is 36.8 ± 0.4ºC, with a nadir at 6 AM and a peak at 4-6 PM. A morning temperature of greater than 37.2ºC and an evening temperature of greater than 37.7ºC is often considered as fever. Fever may be continuous, intermittent or remittent. However, with frequent self-medication with antipyretics, classic patterns are not generally seen. \n" +
            "<h1>Diagnosis</h1>\n" +
            "It is important to work towards finding the cause of fever. A meticulous history of \n" +
            "chronology of symptoms, any associated focal symptom(s), exposure to infectious \n" +
            "agents and occupational history may be useful. A thorough physical examination \n" +
            "repeated on a regular basis may provide potentially diagnostic clues such as rash, \n" +
            "lymphadenopathy, hepatomegaly, splenomegaly, abdominal tenderness, altered \n" +
            "sensorium, neck stiffness, lung crepts, etc. Drug fever should be considered when the \n" +
            "cause of fever is elusive. \n" +
            "<h1>Diagnostic tests </h1>\n" +
            "<img>" +
            "A large range of diagnoses may possibly be the cause of fever. If the history and \n" +
            "physical examination suggest that it is likely to be more than a simple URI or viral \n" +
            "fever, investigations are indicated. The extent and focus of diagnostic work-up will \n" +
            "depend upon the extent and pace of illness, diagnostic possibilities and the immune \n" +
            "status of the host. If there are no clinical clues, the work-up should include a complete \n" +
            "haemogram with ESR, smear for malarial parasite, blood culture, Widal test, urine \n" +
            "analysis including urine culture. If the febrile illness is prolonged beyond 2 weeks, an \n" +
            "X-ray chest is indicated even in the absence of respiratory symptoms. Any abnormal \n" +
            "fluid collection should be sampled. Ultrasonography is needed in some cases of acute \n" +
            "fever such as in amoebic liver abscess. \n" +
            "<h1> Treatment </h1>\n" +
            "Routine use of antipyretics in low-grade fever is not justified. This may mask important clinical indications. However, in acute febrile illnesses suggestive of viral or bacterial cause, fever should be symptomatically treated. \n" +
            "<a href=\"2.1.9\">Nonpharmacological </a>\n" +
            "\n" +
            "Hydrotherapy with tepid water, rest and plenty of oral fluids. \n" +
            "<h1>\n" +
            "Pharmacological</h1>\n" +
            "<h2>Non-specific. </h2>\n" +
            "\n" +
            "Tab. Paracetamol 500-1000 mg (max 4 g in 24 hours) 6-8 hourly. \n" +
            "(<b>Caution:</b> Reduce dose in frail elderly, adults weighing <50 kg and those at risk of hepatotoxicity) \n" +
            "<br>\n" +
            "Or \n" +
            "<br>\n" +
            "Tab. Ibuprofen 400-600 mg 8 hourly. \n" +
            "<br>\n" +
            "<b>Specific.</b> Antibiotics/antimalarials depending upon the cause suggested by clinical and laboratory evaluation. \n" +
            "\n" +
            "<h1>Outcome</h1> \n" +
            "In most cases of fever, patient may either recover spontaneously or a diagnosis is reached after repeated clinical evaluation and investigations. If no diagnosis is reached in up to 3 weeks, patient is said to be having fever of unknown origin (FUO) and should be managed accordingly. \n" +
            "\n" +
            "<h1>Patient education </h1>\n" +
            "<ul>\n" +
            "<li>Self-medication and over-medication should be avoided.</li>\n" +
            "<li>Avoid injectable paracetamol/NSAIDs.</li>\n" +
            "<li>Antibiotics should be taken only on advice of a physician.</li>\n" +
            "<li>Avoid covering the patient having high fever with blanket, etc.</li>\n" +
            "<li>Plenty of fluids should be taken. Stay in cool environment. Washing/sponging of face and limbs should be done repeatedly. </li>\n" +
            "</ul>\n" +
            "  ";
    String u=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_html);
        Intent intent = getIntent();
        getset = (Disease_Fragments) intent.getSerializableExtra("getset");
        html=(TextView) findViewById(R.id.htmltag);
        html.setMovementMethod(LinkMovementMethod.getInstance());
       dbh=new DbHandler(displayHTML.this);

       html.setText(Html.fromHtml(html_str));

        BetterLinkMovementMethod method=BetterLinkMovementMethod.linkifyHtml(html);
      method.setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
          @Override
          public boolean onClick(TextView textView, String url) {
        startActivity(new Intent(displayHTML.this,StockDetails.class).putExtra("Drug",url));
              return false;
          }
      });

        //html.setMovementMethod(LinkMovementMethod.getInstance());

     /*  if(getset.getFragment_id().substring(0,1).equals("3"))
        {
            html.setText(Html.fromHtml(dbh.getHTMLfromSubDisease(getset)));
        }
        else
       {
           html.setText(Html.fromHtml(dbh.getHTMLfromDisease(getset)));
       }*/



    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(displayHTML.this,Main2Activity.class));
        getset.setFragment_id(null);
        finish();
        super.onBackPressed();
    }
}
