
import com.mycompany.sneak.SneakGame;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author GUILHERMEDIASVILLANO
 */
public class SnakeTest {

    SneakGame snakeGame;
    JFrame frame;
    int boardWidth;
    int boardHeight;

    @BeforeEach
    public void setUp() {

        boardWidth = 600; // controla a largula da tela
        boardHeight = boardWidth; // controla a altura da tela 

        frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight); // dedefine o tamanho dele
        frame.setLocationRelativeTo(null); //faz com que a tela fique no meio
        frame.setResizable(false);// não deixa aumentar ou diminuar a tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        snakeGame = new SneakGame(boardWidth, boardHeight);
        frame.add(snakeGame);

        /* 
            Serve para dar o foco do teclado ao componente sneakGame
            Ele diz que voce que que sneakGame (que é o Jframe1) receba os eventos
            do teclado - como as setas para mover a cobrinha
         */
        snakeGame.requestFocus();
               
    }
    
    @Test
    public void testBoardSize(){
       assertEquals( 600 , frame.getHeight());
       assertEquals(600 , frame.getWidth());
    }
    
    @Test
    public void testSnakeHeadPosition(){
        int x = snakeGame.snakeHeadX();
        int y = snakeGame.snakeHeadY();
        
        assertEquals( 5 , x);
        assertEquals( 5 , y);
    }
    
    
    
//    @Test
//    public void TestKeyPressd(){
//        
//        snakeGame.keyPressed(new KeyEvent(snakeGame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP , "w" ));
//        assertEquals(0, snakeGame.velocidadeX);
//        
//    }

}
