public class Driver{
  public static void main(String[] args) {
    KnightBoard b = new KnightBoard(4,5);
    System.out.println(b.solve(2,3));
    System.out.println(b);
    KnightBoard c = new KnightBoard(3,4);
    System.out.println(c.countSolutions(1,2));
  }
}
