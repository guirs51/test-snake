/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sneak;

import com.mycompany.sneak.SneakGame.Tile;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author GUILHERMEDIASVILLANO
 */
//esta tela é responsavel por desenhar o jogo na tela.
class SnakeDraw extends JPanel {

    private SneakGame snakeGame;

    SnakeDraw(SneakGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    //metodo responsavel  por desenhar tood o jogo na tela
    //esse metodo é chamado auomaticamente sempre que o componente grafico
    public void draw(Graphics g) {
        for (int i = 0; i < snakeGame.boardWidth / snakeGame.tileSize; i++){
            g.drawLine(i * snakeGame.tileSize, 0, i * snakeGame.tileSize, snakeGame.boardHeight);
            g.drawLine(0, i * snakeGame.tileSize, snakeGame.boardWidth, i * snakeGame.tileSize);
        }
        g.setColor(Color.red);

        //desenha a comida como um retangulo simples 
        //a posição é calculada multiplincado a posiçõa da comida (em tiles) pelo tamanho do tile em pixels
        //fillReact(x,y,w,h): desenha um retangulo 2d prenchido
        g.fillRect(
                snakeGame.food.x * snakeGame.tileSize,
                snakeGame.food.y * snakeGame.tileSize,
                snakeGame.tileSize,
                snakeGame.tileSize
        );

        g.setColor(Color.GREEN);

        g.fillRect(
                snakeGame.snakeHead.x * snakeGame.tileSize,
                snakeGame.snakeHead.y * snakeGame.tileSize,
                snakeGame.tileSize,
                snakeGame.tileSize
        );

        for (int i = 0; i < snakeGame.snakeBody.size(); i++) {
            //recupera a posição de cada pedaço do corpo da cobra
            Tile snakePart = snakeGame.snakeBody.get(i);

            // desenha esse epedaço como um retangulo verde
            g.fillRect(
                    snakePart.x * snakeGame.tileSize,
                    snakePart.y * snakeGame.tileSize,
                    snakeGame.tileSize,
                    snakeGame.tileSize
            );

        }

        g.setFont(new Font("Arial", Font.PLAIN, 16));

        if (snakeGame.gameOver) {
            g.setColor(Color.red);
            g.drawString("Gamer Over: " + String.valueOf(snakeGame.snakeBody.size()), snakeGame.tileSize - 16, snakeGame.tileSize);
        } else {
            g.drawString("Score: " + String.valueOf(snakeGame.snakeBody.size()), snakeGame.tileSize - 16, snakeGame.tileSize);
        }

    }
}
