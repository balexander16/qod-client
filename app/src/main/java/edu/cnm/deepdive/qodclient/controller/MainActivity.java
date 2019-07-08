package edu.cnm.deepdive.qodclient.controller;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import edu.cnm.deepdive.qodclient.R;
import edu.cnm.deepdive.qodclient.model.Quote;
import edu.cnm.deepdive.qodclient.viewmodel.MainViewModel;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private MainViewModel viewModel;
  private LiveData<Quote> random;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupToolbar();
    setupFab();
    setupViewModel();
  }

  private void setupViewModel() {
    View rootView = findViewById(R.id.root_view);
    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getRandomQuote().observe(this, (quote) -> {
      AlertDialog dialog = new AlertDialog.Builder(this)
          .setTitle("Random Quote") //TODO extract to resource.
          .setMessage(quote.getText() + quote.getCombinedSources())
          .setPositiveButton("Cool!", (dialogInterface, i) -> {})
          .create();
      dialog.show();
    });

//    Button newSearchButton = findViewById(R.id.button);
//    final EditText newSearchQuote = findViewById(R.id.edit_text);
//    newSearchButton.setOnClickListener(new OnClickListener() {
//      @Override
//      public void onClick(View v) {
//      }
//    });
      //FIXME Do this right......
  }

  private void setupFab() {
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> viewModel.getRandomQuote());
  }

  private void setupToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }


}
