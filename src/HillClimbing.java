import java.util.*;

/**
 * Classe HillClimbing.
 * Algoritmo de IA que usamos para resolver o problema n-queens
 * actual E o estado atual
 * @author Daniel Fernandes
 * @author Diogo Zacarias
 * @author Kartic Premgi
 * @version 1.0
 */
public class HillClimbing {
    private static State actual;
    List<Ilayout> checked;

    /**
     * Classe State
     * Um estado e representado por um ilayout e um h.
     * Ilayout - board do estado
     * h - representa a heuristica
     */
    static class State {
        private Ilayout layout;
        private double h;

        /**
         * Construtor do State
         * h - representa o valor da heuristica
         * @param l - board que representa o Ilayout
         */
        public State(Ilayout l) {
            layout = l;
            h = l.getH();
        }

        /**
         * toString retorna o layout em toString do Board
         * @return layout em String
         */
        public String toString() { return layout.toString(); }

        /**
         * Getter do H
         * @return o valor do h
         */
        public double getH() {return h;}

        /**
         * @return o valor da hashcode
         */
        public int hashCode() {
            return toString().hashCode();
        }
    }

    /**
     * Funcao que calcula o melhor caso dos filhos do Estado Atual
     * @param n representa o estado pai
     * @pre n != null
     * @return estado o melhor filho do estado recebido
     */
    private State melhor_caso(State n) {
        List<Ilayout> children = n.layout.children();  ///cria os filhos
        State melhor_caso = new State(children.get(0));
        for (int i = 1; i < children.size(); i++) {
            State nn = new State(children.get(i));
            if(!checked.contains(nn.layout)){
                if(nn.getH() < melhor_caso.getH()){
                    melhor_caso = nn;
                }
            }
        }
        return melhor_caso;
    }

    /**
     * Funcao que poe em pratica o algoritmo "HillClimbing"
     * @param n representa a largura do quadro
     * @pre n > 3
     * @return a primeira solucao encontrada
     */
    public Board solve(int n) {
        checked = new ArrayList<>();
        Board initial = new Board(n);
        actual = new State(initial);
        int contador=0;
        while (true) {
            if(checked.size()>99){
                checked.remove(0);
            }
            checked.add(actual.layout);
            if (actual.layout.isGoal((int) actual.getH())) {
                return (Board) actual.layout;
            }
            else{
                State melhor_case = melhor_caso(actual);
                if(melhor_case.getH() < actual.getH()){
                    actual = melhor_case;
                }
                else if(contador<50 && melhor_case.getH() == actual.getH()){
                    actual = melhor_case;
                    contador++;
                }
                else{
                    contador=0;
                    actual = new State(new Board(n));
                    while(checked.contains(actual.layout)){
                        actual = new State(new Board(n));
                    }
                }
            }
        }
    }


}
