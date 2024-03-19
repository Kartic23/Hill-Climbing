import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class UnitTest {

    @Test
    public void test_GetH() {
        Board b1 = new Board(2,3,1,3);
        Assert.assertEquals(2.0, b1.getH());
        Board b2 = new Board(1,2,3,0);
        Assert.assertEquals(4.0, b2.getH());

    }

    @Test
    public void test_colisoes() {
        Board b1 = new Board(2,3,1,3);
        Assert.assertEquals(2.0, b1.colisoes(b1));

        Board b2 = new Board(1,2,3,0);
        Assert.assertEquals(4.0, b2.colisoes(b2));

    }

    @Test
    public void test_n_colision() {
        Board b1 = new Board(2,3,1,3);
        ArrayList<Integer> posicoes = new ArrayList<Integer>();
        posicoes.add(2);
        posicoes.add(3);
        posicoes.add(1);
        posicoes.add(3);
        Assert.assertEquals(1, b1.n_collision(b1,0));
        Assert.assertEquals(1, b1.n_collision(b1,1));
        Assert.assertEquals(0, b1.n_collision(b1,2));
    }

    @Test
    public void test_make_children(){
        Board b1 = new Board(2,3,1,3);
        List<Ilayout> result = b1.make_children(b1);
        Board b2 = new Board(0,3,1,3);
        Board b3 = (Board) result.get(0);
        Assert.assertEquals(b2.toString(),b3.toString());
    }

    @Test
    public void test_sucessores_da_linha(){
        Board b1 = new Board(2,3,1,3);
        List<Ilayout> result = new ArrayList<>();
        b1.sucessores_da_linha(b1,0,result);
        Board b2 = new Board(1,3,1,3);
        Board b3 = (Board) result.get(1);
        Assert.assertEquals(b2.toString(),b3.toString());
    }

    @Test
    public void test_Board(){
        int[] b1 = new int[4];
        Board b3 = new Board(b1,0,0,4);
        Board b2 = new Board(0,0,0,0);
        Assert.assertEquals(b2.toString(),b3.toString());
    }
}
