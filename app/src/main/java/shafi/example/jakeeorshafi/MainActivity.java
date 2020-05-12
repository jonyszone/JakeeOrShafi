package shafi.example.jakeeorshafi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    enum Player{
        ONE,TWO,No
    }
    Player currentPlayer = Player.ONE;
    Player [] playerChoices = new Player[9];

    int [][] winnerRowsColumns = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6},};
    private boolean gameOver = false;
    private Button btnAgin;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerChoices [0] = Player.No;
        playerChoices [1] = Player.No;
        playerChoices [2] = Player.No;
        playerChoices [3] = Player.No;
        playerChoices [4] = Player.No;
        playerChoices [5] = Player.No;
        playerChoices [6] = Player.No;
        playerChoices [7] = Player.No;
        playerChoices [8] = Player.No;

        btnAgin = findViewById(R.id.btnAgain);
        gridLayout = findViewById(R.id.gridLayout);

        btnAgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetThegame();
            }
        });


    }

    public void tapImageView(View imageView) {
        ImageView tapImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tapImageView.getTag().toString());
        if (playerChoices[tiTag] == Player.No && !gameOver) {
            tapImageView.setTranslationX(-2000);


            playerChoices[tiTag] = currentPlayer;
            if (currentPlayer == Player.ONE) {
                tapImageView.setImageResource(R.drawable.jakee);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tapImageView.setImageResource(R.drawable.shafi);
                currentPlayer = Player.ONE;
            }

            tapImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
            Toast.makeText(this, tapImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]] && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                        && playerChoices[winnerColumns[0]] != Player.No) {

                    btnAgin.setVisibility(View.VISIBLE);
                    gameOver = true;
                    String winnerOfgame = "";
                    if (currentPlayer == Player.ONE) {
                        winnerOfgame = "Player Two";
                    } else if (currentPlayer == Player.TWO) {
                        winnerOfgame = "Player One";
                    }
                    Toast.makeText(this, winnerOfgame + " is the WINNER", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    //Method fof Play  again
    private void resetThegame(){
        for (int index = 0; index < gridLayout.getChildCount();index++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(index);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.4f);
        }

        currentPlayer = Player.ONE;
        playerChoices [0] = Player.No;
        playerChoices [1] = Player.No;
        playerChoices [2] = Player.No;
        playerChoices [3] = Player.No;
        playerChoices [4] = Player.No;
        playerChoices [5] = Player.No;
        playerChoices [6] = Player.No;
        playerChoices [7] = Player.No;
        playerChoices [8] = Player.No;

        gameOver = false;
        btnAgin.setVisibility(View.INVISIBLE);

    }
}
