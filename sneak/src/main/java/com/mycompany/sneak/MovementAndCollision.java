/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sneak;

import com.mycompany.sneak.SneakGame.Tile;
import java.awt.event.KeyEvent;

/**
 *
 * @author GUILHERMEDIASVILLANO
 */
class MovementAndCollision {

    private SneakGame snakeGame;

    MovementAndCollision(SneakGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    public void move() {

        if (snakeGame.collision(snakeGame.snakeHead, snakeGame.food)) {
            //come a comida
            snakeGame.snakeBody.add(snakeGame.new Tile(snakeGame.food.x, snakeGame.food.y));

            snakeGame.placeFood();
        }

        for (int i = snakeGame.snakeBody.size() - 1; i >= 0; i--) {

            Tile snakePart = snakeGame.snakeBody.get(i);

            if (i == 0) {
                snakePart.x = snakeGame.snakeHead.x;
                snakePart.y = snakeGame.snakeHead.y;
            } else {
                Tile prevSnakePart = snakeGame.snakeBody.get(i);

                //atualiza a posição X e Y com a posicão do segmneto anterior
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }

        }

        // Move a Cabeça da cobra
        snakeGame.snakeHead.x += snakeGame.velocidadeX;
        snakeGame.snakeHead.y += snakeGame.velocidadeY;

        for (int i = 0; i < snakeGame.snakeBody.size(); i++) {
            //recupera o segmento atual do corpo
            Tile snakePart = snakeGame.snakeBody.get(i);

            //Verifica colisao com a cabeça
            if (snakeGame.collision(snakeGame.snakeHead, snakePart)) {
                //O jogo termina se colider
                snakeGame.gameOver = true;
            }
        }

        //Verifica a colisão co as paredes
        if (snakeGame.snakeHead.x * snakeGame.tileSize < 0 || snakeGame.snakeHead.x > snakeGame.boardWidth
                || snakeGame.snakeHead.y * snakeGame.tileSize < 0 || snakeGame.snakeHead.y > snakeGame.boardHeight) {
            //O jogo termina 
            snakeGame.gameOver = true;
        }
    }

    public boolean collision(Tile tile1, Tile tile2) {
        //Verifiva se as coodernadas X e Y dos dois objetos Tile são iguais
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    //metodo chamado quando uma tecla é presionda
    public void keyPressed(KeyEvent tecla) {
        //verefica o codigo da tecla pressionada
        switch (tecla.getKeyCode()) {
            case KeyEvent.VK_UP:
                //se a tecla pressionada for VK_UP (seta para cima)
                //e a velocidade vertical atual não for +1 (para baixo),
                //e velocidade horizontal é definida como O e a velocidade
                //Vertcal é definido como -1 (para cima).
                if (snakeGame.velocidadeY != 1) {
                    snakeGame.velocidadeX = 0;
                    snakeGame.velocidadeY = -1;
                }
                break;
            case KeyEvent.VK_DOWN:
                //se a tecla pressionada for VK_DOWN (seta pra baixo)
                //e a velocidade vertical atual não for -1 (para cima)
                //a velocidade horizontal é definidade como ) e a velocidade vertical
                //é definida com 1 (para baixa)
                if (snakeGame.velocidadeY != -1) {
                    snakeGame.velocidadeX = 0;
                    snakeGame.velocidadeY = 1;
                }
                break;
            case KeyEvent.VK_LEFT:
                //se a tecla pressionada for VK_LEFT (seta pra esquerda)
                //e a velocidade horizontal atual não for 1 (para direita)
                //e a velocidade horizontal é definidade como -1 (para esquerda e a velocideade vertical defi
                if (snakeGame.velocidadeY != -1) {
                    snakeGame.velocidadeX = -1;
                    snakeGame.velocidadeY = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                //se a tecla pressionada for VK_RIGHT (seta pra direita)
                //e a velocidade horizontal atual não for -1 (para esquerda)
                if (snakeGame.velocidadeY != -1) {
                    snakeGame.velocidadeX = 1;
                    snakeGame.velocidadeY = 0;
                }
                break;
        }

    }

}
