/*
Samardzic, Ivan - 20296563
April 6, 2023
The following code was adapted from the provided code for GuessMaster 1.0/2.0
All non-commented lines are self-explanatory, and/or used from the previous assignment.
*/

package com.example.guessmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.os.Bundle;
import java.util.Random;
import android.content.DialogInterface;

public class GuessMaster extends AppCompatActivity {
    //Declaration of view components.
    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private ImageView entityImage;
    private String user_input;
    String answer;

    //Declaration of instance variables.
    private int numOfEntities;
    private Entity[] entities;
    private int totalTicketNum = 0;
    private int entityId = 0;
    //Counter variable.
    private int count = 0;


    //Sample entities to be used.
    Politician jTrudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), "Male", "Liberal", 0.25);
    Singer cDion = new Singer("Celine Dion", new Date("March", 30, 1961), "Female", "La voix du bon Dieu", new Date("November", 6, 1981), 0.5);
    Person myCreator = new Person("My Creator", new Date("September", 1, 2000),"Female", 1);
    Country usa = new Country("United States", new Date("July", 4, 1776), "Washington D.C.", 0.1);

    //OnCreate() method.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_activity);

        //Definition of all view components.
        entityName = (TextView)findViewById(R.id.entityName);
        ticketsum = (TextView)findViewById(R.id.ticket);
        guessButton = (Button)findViewById(R.id.btnGuess);
        userIn = (EditText)findViewById(R.id.guessinput);
        btnclearContent = (Button)findViewById(R.id.btnClear);
        entityImage = (ImageView)findViewById(R.id.entityImage);

        //Adding sample entities into the list.
        new GuessMaster();
        addEntity(jTrudeau);
        addEntity(cDion);
        addEntity(myCreator);
        addEntity(usa);

        //Call changeEntity() to prepare the first entity to be guessed.
        changeEntity();
        //Increase the counter after changeEntity() has been called.
        count++;

        //Button definition.
        btnclearContent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                changeEntity();
            }
        });
        //Button definition.
        guessButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playGame();
            }
        });
    }

    //Default constructor.
    public GuessMaster() {
        numOfEntities = 0;
        entities = new Entity[10];
    }

    //addEntity method to add entities into the list.
    public void addEntity(Entity entity) {
        entities[numOfEntities++] = entity.clone();
    }

    //genRandomEntityId method to choose a number within the range of entities.
    public int genRandomEntityId() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(numOfEntities);
    }

    //changeEntity method to randomly choose the next entity.
    public void changeEntity(){
        //Make a call to the Continuegame() method.
        //Both methods preform the same action.
        //No need to rewrite code, the provided method is called instead.
        ContinueGame();
    }

    //ImageSetter method to set a picture for the appropriate entity.
    public void ImageSetter(Entity entity){
        //Comparing each entity to the current entity.
        //Assigning true if equal, otherwise false.
        Boolean jT = entity.toString().equals(jTrudeau.toString());
        Boolean cD = entity.toString().equals(cDion.toString());
        Boolean mC = entity.toString().equals(myCreator.toString());
        Boolean uS = entity.toString().equals(usa.toString());

        //Set the corresponding image to the correct entity if true.
        if (jT) {
            entityImage.setImageResource(R.drawable.justint);
        }
        else if (cD) {
            entityImage.setImageResource(R.drawable.celidion);
        }
        else if (mC) {
            entityImage.setImageResource(R.drawable.mycreator);
        }
        else if (uS) {
            entityImage.setImageResource(R.drawable.usaflag);
        }
    }

    //welcomeToGame method to alert a welcome message.
    public void welcomeToGame(Entity entity){
        AlertDialog.Builder welcomealert = new AlertDialog.Builder(GuessMaster.this);
        welcomealert.setTitle("GuessMaster Game v3");
        welcomealert.setMessage(entity.welcomeMessage());
        welcomealert.setCancelable(false);

        welcomealert.setNegativeButton("START GAME", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialop, int which) {
                Toast.makeText(getBaseContext(), "Game is Starting... Enjoy", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = welcomealert.create();
        dialog.show();
    }

    //playGame method to check the user-inputted answer.
    public void playGame() {
        Entity entity = entities[entityId];
        answer = userIn.getText().toString();
        answer = answer.replace("\n", "").replace("\r", "");
        Date date = new Date(answer);

        if (date.precedes(entity.getBorn())) {
            //Create an alert.
            AlertDialog.Builder laterDateAlert = new AlertDialog.Builder(GuessMaster.this);
            laterDateAlert.setTitle("Incorrect");
            laterDateAlert.setMessage("Try a later date");
            laterDateAlert.setIcon(R.drawable.ic_error_outline_black_24dp);
            laterDateAlert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getBaseContext(), "Try again...", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = laterDateAlert.create();
            dialog.show();

        } else if (entity.getBorn().precedes(date)) {
            //Create an alert.
            AlertDialog.Builder earlierDateAlert = new AlertDialog.Builder(GuessMaster.this);
            earlierDateAlert.setTitle("Incorrect");
            earlierDateAlert.setMessage("Try an earlier date");
            //Include the provided image in the alert.
            earlierDateAlert.setIcon(R.drawable.ic_error_outline_black_24dp);
            earlierDateAlert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getBaseContext(), "Try again...", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = earlierDateAlert.create();
            dialog.show();

        } else {
            //Create an alert.
            AlertDialog.Builder correctDateAlert = new AlertDialog.Builder(GuessMaster.this);
            correctDateAlert.setTitle("You won");
            correctDateAlert.setMessage("BINGO! "+entity.closingMessage());
            //Include the provided image in the alert.
            correctDateAlert.setIcon(R.drawable.ic_check_circle_black_24dp);
            correctDateAlert.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getBaseContext(), "Tickets Won: " + entity.getAwardedTicketNumber(), Toast.LENGTH_SHORT).show();
                    //Make a call to continue the game.
                    ContinueGame();
                    //Update the running total ticket number. Make it visible.
                    totalTicketNum += entity.getAwardedTicketNumber();
                    ticketsum.setText("Ticket Total: "+totalTicketNum);
                }
            });
            AlertDialog dialog = correctDateAlert.create();
            dialog.show();
        }
    }

    //ContinueGame method to randomly update a new entity.
    public void ContinueGame(){
        entityId = genRandomEntityId();
        Entity entity = entities[entityId];
        //Only display the welcome message the first time.
        //count = 0 on the first call to this method.
        if(count<1) {
            welcomeToGame(entity);
        }
        ImageSetter(entity);
        entityName.setText(entity.getName());
        userIn.getText().clear();
    }
}