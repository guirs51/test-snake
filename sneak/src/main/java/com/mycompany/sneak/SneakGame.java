/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sneak;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author GUILHERMEDIASVILLANO
 */
public class SneakGame extends JPanel implements ActionListener, KeyListener {

    // Esta classe representa um unico quadrado do tabuleiro
    public class Tile {

        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;  // a largura do tabuleiro em pixels
    int boardHeight; // a altura do tabuleiro em pixels
    int tileSize = 25; //o tamanho do um quadrado do tabuleiro 

    public Tile snakeHead; // A posição da cabeça da cobra
    ArrayList<Tile> snakeBody; // um array qye guarda as posicoes do corpo da cobra
    Tile food; // guarda a posição da comida
    Random random; //um objeto Random utilizado para posicionar a comida aleatoriamente.

    public int velocidadeX; // a direção horizontal da cobra (1 = direta, -1 = esquerda, 0 = parado).
    public int velocidadeY; //a direção vertical da cobra ( 1 = para baixo, -1 para cima, 0 = parado)

    Timer gameLoop; // um timer que atualiza o joga a cada 100 milisegundos.
    boolean gameOver = false; //indica se o jogo acabou

    MovementAndCollision movementAndCollision;
    SnakeDraw snakeDraw;

    /* Construtor da classe Snakegame */
    public SneakGame(int boardWidth, int boardHeight) {

        //=========================DEFINE O TAMANHO E APARENCIA DO PAINEL DO JOGO=======================================================

        /* Atribuem a largura e altura do tabuleiro
       fornecidas ao campos correspondentes da classe. 
         */
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        /*
        Define o tamanho preferido do painel do jogo
    para o tamanho do tabuleiro
         */
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));

        /*  Define a cor de fundo do painel para preto.*/
        setBackground(Color.black);

        //============================================================================================================================
        //=================================ADICIONA SUPORTE PARA EVENTOS DO TECLADO===================================================
        /* Adciona o proximo objeto snakegame como um listener
    do evento de teclado. isso permite que o jogo capture
    pressionamentos de telcas e reaja de acordo*/
        addKeyListener(this);

        setFocusable(true);
        //=============================================================================================================================

        //=================================INICIALIZA A COBRA E A COMIDA===============================================================
        /* cria um novo objeto Tile  representando a cabeça da cobra e a posicionaa no
    centro do tabuleiro (coordenadas 5, 5) */
        snakeHead = new Tile(5, 5);

        /* cria uma nova arraylist para armazenar as posiçoes dos segmentos do corpo da cobra */
        snakeBody = new ArrayList<>();

        /* cria um novo objeto Tile para representar a comida e a posiciona em uma coordenada aleatoria ( inicialmente 10, 10) */
        food = new Tile(10, 10);

        //cria um objeto Random para gerar numeros aleatorias utilizados para posicionar a comida
        random = new Random();

        //chama a metodo pracleFood para posicionar a comida aleatoria mente no tabuleiro
        placeFood();

        //===========================================================================================================================
        //==============================================DEFINE A DIREÇÃO E VELOCIDADE DA COBRA=======================================
        /*define a direção horizontal inicial da cobra para a direita (velocidade de 1 pixels por atualizaçã0 */
        velocidadeX = 1;
        /*define a direçao vertical inicila da cobra para nenhuma (velocidade de 0 pixels por atualização) */
        velocidadeY = 0;

        //==========================================================================================================================
        //============================================INICIALIZA O OBEJETO AUXILIARES===============================================
        /*cria um objeto movenmnetAndCOllision passando o proprio objeto SnakeGamecomo argumento.
    esta calasse é responsavel pela logica de movemneto da coba e detecção de colisoes. 
         */
        movementAndCollision = new MovementAndCollision(this);

        //cria um objeto snakeDraw passando o proprio obejeto snakeGame como argumento
        //esta classe é responsavel por desenhar o jogo a tela
        snakeDraw = new SnakeDraw(this);

        //===============================================================================================================================
        //====================INICIALIZA LOOP PRINCIPAL DO JOGO==========================================================================
        //cria um objeto Timer que gera um evento a cada 100 meliisegundos e passa o proprio objetoSnakecomo listener. 
        gameLoop = new Timer(1000, this);

        //inicia o timer, que essenciamnete inicia o loop principal do jogo 
        gameLoop.start();

        //====================================================================================================================================
    }

    public int snakeHeadX() {
        return snakeHead.x;
    }

    public int snakeHeadY() {
        return snakeHead.y;
    }

    //metodo que posiciona a comida aleatoriamente no tabuliero.
    public void placeFood() {
        //gera um numero aleatorio ente 0 e boardWidth / tileSiz -1 para a coordenada x da comida 
        int posX = random.nextInt(boardWidth / tileSize);

        //verefica se a coodernada X gerada é igual a 10 (o centro do tabuleiro
        while (posX == 10) {
            posX = random.nextInt(boardWidth / tileSize);
        }
        food.x = posX;

        //gera um numero aleatorio ente 0 e boardWidth / tileSize -1 para a coodernada Y da comida 
        int posY = random.nextInt(boardWidth / tileSize);

        //verefica se a coodernada Y gerada é igual a 10 (o centro do tabuleiro
        while (posY == 10) {
            posY = random.nextInt(boardWidth / tileSize);
        }
        food.y = posY;
    }

    //Metodo que chama o move(), que move a cobra de acordo com a sua direção
    public void move() {
        movementAndCollision.move();
    }

    //metodo que chama collision que verefica se duas peças do jogo estao colidindo
    public boolean collision(Tile tile1, Tile tile2) {
        return movementAndCollision.collision(tile1, tile2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();// o metodo repaint() é implementado pela classe JPpanel

        //verefica se o jogo terminou
        if (gameOver) {
            gameLoop.stop();
        }
    }

    /*
   Metodo pra desenha o jogo na tela
   Esta metodo é chamado automaticamente pelo Swing sempre 
 que o componente precisa ser redesenhado(pro exemplos, ao iniciar a janela, 
  ao redimensionar ou atualizar o jogo).
  
   O parametro Graphics g é contexto grafico , ou seja, o que voce usa 
 pra desena na tela (linhas, formas, imagens, etc)
  
     */
    @Override
    //metodo chamado quando uma tecla é pressionada
    public void keyPressed(KeyEvent tecla) {
        movementAndCollision.keyPressed(tecla);
    }

    @Override
    //metodo chamado qunado uma tecla pe digitada
    public void keyTyped(KeyEvent e) {
    }

    @Override
    //Metodo chamado qunado uma tecla é liberado
    public void keyReleased(KeyEvent e) {
    }

    //metodo que desena o jogo na tela
    @Override
    public void paintComponent(Graphics g) {
        /* garante que tudo que o JPanel precisa desenhar normalmente 
        (como fundo) seja feito antes de voce desenhar seus proprios elementos.*/
        super.paintComponent(g);
        /*
        Esta sendo usado para desenhar a parte da cobra e a comida
      na tela dentro do painel principal
         */
        snakeDraw.paintComponent(g);
    }
}
