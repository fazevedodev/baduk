/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import board.Board;

/**
 *
 * @author Fabiano
 */
public class ReplayStatus {
    public Board board;
    public int wCaptures;
    public int bCaptures;
    
    public ReplayStatus(Board b, int bcaps, int wcaps) {
        board = b;
        bCaptures = bcaps;
        wCaptures = wcaps;
    }
}
