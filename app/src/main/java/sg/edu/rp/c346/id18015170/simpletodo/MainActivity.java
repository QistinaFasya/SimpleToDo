package sg.edu.rp.c346.id18015170.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTodo;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTodo;
    Spinner spnTodoIndex;

    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTodo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTodo = findViewById(R.id.ListViewToDo);
        spnTodoIndex = findViewById(R.id.spinner);



        spnTodoIndex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        //
                        etTodo.setHint("Type in a new task here");
                        etTodo.setText("");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        //
                        etTodo.setHint("Type in index of of the task to be removed");
                        etTodo.setText("");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        alTodo = new ArrayList<>();
        aaTodo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTodo);
        lvTodo.setAdapter(aaTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    alTodo.add(etTodo.getText().toString());
                    aaTodo.notifyDataSetChanged();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etTodo.setText("");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String position = etTodo.getText().toString();
                if (alTodo.size()==0){
                    String msg = "You don't have any task to remove";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }
                else if(Integer.parseInt(position) > alTodo.size()-1){
                    String msg = "Wrong index number";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                }
                else{
                    alTodo.remove(Integer.parseInt(position)-1);
                    aaTodo.notifyDataSetChanged();
                }


            }
        });

    }
}
