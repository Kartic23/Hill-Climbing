import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Classe Board.
 * Classe onde manipulamos o board
 * @author Daniel Fernandes
 * @author Diogo Zacarias
 * @author Kartic Premgi
 * @version 1.0
 */
public class Board implements Ilayout{
    private final int dim;
    private int[] board;

    /**
     * Construtor que cria Board inicial
     * @param n - dimensões do board
     */
     public Board(int n){
        Random rand = new Random();
        this.dim = n;
        this.board = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = rand.nextInt(n - 1);
        }
     }


    /**
     * toString retorna o board em string
     * @return - board em string
     */
    public String toString(){
        String result = "";
        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                if(j==this.board[i])
                    result += "1 ";
                else
                    result += "0 ";
            }
            result += '\n';
        }
        return result;
    }

    /**
     * Construtor que cria um Board para cada filho
     * @param quadro - board anterior
     * @param line - linha da rainha
     * @param col - coluna da rainha
     * @param n - dimensões do board
     */
    public Board(int[] quadro, int line, int col, int n){
        this.dim = n;
        this.board = new int[dim];
        for(int i = 0; i < dim; i++){
            if(i==line)
                this.board[i]=col;
            else
                this.board[i]=quadro[i];

        }
    }

    /**
     * Funcao que coloca numa lista os filhos possiveis da rainha da linha dada
     * @param b - board atual
     * @param line - representa a linha da rainha
     * @param result lista de todos os movimentos possiveis da rainha na linha atual
     */
    public void sucessores_da_linha(Board b, int line, List<Ilayout> result){
        for (int col = 0; col < dim; col++) {
            if(b.board[line] != col){
                result.add(new Board(b.board,line,col,dim));
            }
        }
    }

    /**
     * Funcao que cria que uma lista com os todos os filhos criados e retona a mesma lista
     * @param b - board atual
     * @return - retorna a lista com os filhos criados
     */
    public List<Ilayout> make_children(Board b){
        List<Ilayout> result = new ArrayList<>();
        for (int line = 0; line < dim; line++) {
            sucessores_da_linha(b,line,result);
        }
        return result;
    }

    /**
     * Funcao que retorna a lista dos filhos criados
     * @return retorna a lista dos filhos criados
     */
    @Override
    public List<Ilayout> children() {
        return make_children(this);
    }

    /**
     * Funcao que calcula o numero de colisoes da rainha
     * @param b - board atual
     * @param line - posicao da linha da rainha
     * @return - retorna o numero total de colisoes da rainha
     */
    public int n_collision(Board b,int line) {
        int colisions = 0;
        for (int i = line; i < dim - 1; i++) {
            if (b.board[line] == b.board[i + 1]) {
                colisions++;
            }
            if (b.board[line] + line == b.board[i + 1] + (i + 1)) {
                colisions++;
            }
            if (b.board[line] - line == b.board[i + 1] - (i + 1)) {
                colisions++;
            }
        }
        return colisions;
    }

    /**
     * Funcao que retorna o numero de colisoes do board
     * @param b - board atual
     * @return - retorna o numero total de colisoes do board
     */
    public int colisoes(Board b){
        int result=0;
        for (int line = 0; line < dim; line++) {
            result += n_collision(b,line);
        }
        return result;
    }

    @Override
    public double getH() {
        return colisoes(this);
    }

    /**
     * @param h - recebe o valor de h
     * @return - retorna true se o valor de h = 0, e false caso contrário
     */
    @Override
    public boolean isGoal(int h) {
        return (h == 0);
    }

    /**
     * Construtor do Board para unit_test
     * @param i1 - indice da primeira coluna
     * @param i2 - indice da segunda coluna
     * @param i3 - indice da terceira coluna
     * @param i4 - indice da quarta coluna
     * Construtor usado para testes
     */
    public Board(int i1, int i2, int i3, int i4) {
        this.dim = 4;
        this.board = new int[4];
        this.board[0]=i1;
        this.board[1]=i2;
        this.board[2]=i3;
        this.board[3]=i4;
    }
}