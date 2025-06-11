/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sneak;

import javax.swing.JFrame;

/**
 *
 * @author GUILHERMEDIASVILLANO
 */
public class Sneak {

    public static void main(String[] args) {
        int boardWidth = 600; // controla a largula da tela
        int boardHeight = boardWidth; // controla a altura da tela 
        
        JFrame frame = new JFrame("Snake"); // cria um JFrame novo
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight); // dedefine o tamanho dele
        frame.setLocationRelativeTo(null); //faz com que a tela fique no meio
        frame.setResizable(false);// não deixa aumentar ou diminuar a tela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        SneakGame sneakGame = new SneakGame(boardWidth, boardHeight);
        frame.add(sneakGame);
        
        /* 
            Serve para dar o foco do teclado ao componente sneakGame
            Ele diz que voce que que sneakGame (que é o Jframe1) receba os eventos
            do teclado - como as setas para mover a cobrinha
        */
        sneakGame.requestFocus();
    }
}
