import static org.junit.Assert.assertEquals;

import java.util.Random;
import org.junit.Test;

public class FractionTest {

  private static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

  /**
   * 测试所有分子，和正数分母，使用constructor
   */
  @Test
  public void setUpGoodFractionByConstractorTestFractionItself() {
    Fraction f;
    Random r = new Random();
    int n = 1000000; //这个是正数分母范围

    for (int i = 0; i < 10000; i++) {
      int a = r.nextInt();
      //System.out.println(a);
      int b = r.nextInt(n) + 1; //避免出现0
      f = new FractionImpl(a, b);

      int maxCommonDivider = gcd(a, b);
      if (maxCommonDivider < 0 ) {maxCommonDivider = -maxCommonDivider;}
      int exceptA = a / maxCommonDivider;
      int exceptB = b / maxCommonDivider;

      String except = "your fraction is " + exceptA + "/" + exceptB + ".";
      assertEquals(except, f.toString());
      assertEquals(a, f.getNumerator());
      assertEquals(b, f.getDenominator());
    }
  }


  /**
   * 创建Fraction实例，新建实例为默认值（1/1） 检测使用setter
   */
  @Test
  public void setUpGoodFractionBySettersTestFractionItself() {
    FractionImpl f;
    Random r = new Random();
    int n = 1000000;
    f = new FractionImpl(); //这个是默认分数

    for (int i = 0; i < 10000; i++) {
      int a = r.nextInt();
      //System.out.println(a);
      int b = r.nextInt(n) + 1; //避免出现0
      f.setNumerator(a);
      f.setDenominator(b);

      int maxCommonDivider = gcd(a, b);
      if (maxCommonDivider < 0 ) {maxCommonDivider = -maxCommonDivider;}
      int exceptA = a / maxCommonDivider;
      int exceptB = b / maxCommonDivider;

      String except = "your fraction is " + exceptA + "/" + exceptB + ".";
      assertEquals(except, f.toString());
      assertEquals(a, f.getNumerator());
      assertEquals(b, f.getDenominator());
    }
  }

  /**
   * 测试负数分母和0分母 use constructor at beginning to test non-positive Deno
   */
  @Test(expected = IllegalArgumentException.class)
  public void setUpBadFractionByConstructorTestFractionItself() throws Exception {
    FractionImpl f;
    Random r = new Random();
    int n = 1000000; //这个是正数分母范围

    for (int i = 0; i < 10000; i++) {
      int a = r.nextInt();
      //System.out.println(a);
      int b = -(r.nextInt(n)); //这里包含0
      f = new FractionImpl(a, b);
    }
  }

  /*
   * 测试负数分母和0分母
   * use setter at beginning to test non-positive Deno
   */
  @Test(expected = IllegalArgumentException.class)
  public void setUpBadFractionBySetterTestFractionItself() throws Exception {
    FractionImpl f;
    Random r = new Random();
    int n = 1000000; //这个是正数分母范围

    for (int i = 0; i < 10000; i++) {
      int a = r.nextInt();
      //System.out.println(a);
      int b = -(r.nextInt(n)); //这里包含0
      f = new FractionImpl();
      f.setNumerator(a);
      f.setDenominator(b);
    }
  }

  /**
   * 倒数method检测
   */
  @Test
  public void reciprocal() {
    FractionImpl f;
    Random r = new Random();
    int n = 1000000; //这个是正数分母范围

    for (int i = 0; i < 10000; i++) {
      int a = r.nextInt();
      //System.out.println(a);
      int b = r.nextInt(n) + 1; //避免出现0
      f = new FractionImpl(a, b);

      if (a > 0) {
        int exceptDeno = a;
        int exceptNumo = b;
        Fraction exceptFraction = new FractionImpl(exceptNumo, exceptDeno);
        assertEquals(exceptFraction.toString(), f.reciprocal().toString());
      } else if (a < 0) {
        int exceptDeno = -a;
        int exceptNumo = -b;
        Fraction exceptFraction = new FractionImpl(exceptNumo, exceptDeno);
        assertEquals(exceptFraction.toString(), f.reciprocal().toString());
      } else {
        assertEquals(new IllegalArgumentException(), new FractionImpl(b, a));
      }
    }
  }

  @Test
  public void testCompareTo() {
    FractionImpl f, f2;
    Random r = new Random();
    int n = 1000000; //这个是正数分母范围

    for (int i = 0; i < 10000; i++) {
      int a = r.nextInt();
      int b = r.nextInt(n) + 1; //避免出现0
      int a2 = r.nextInt();
      int b2 = r.nextInt(n) + 1;
      System.out.println(a);
      System.out.println(a2);
      System.out.println(b);
      System.out.println(b2);
      f = new FractionImpl(a, b);
      f2 = new FractionImpl(a2, b2);
      System.out.println(f.toString() + f2.toString());

      //f大于f2
      if ((a * b2) > (a2 * b)) {
        assertEquals(1, f.compareTo(f2));
      }
      else if ((a * b2) < (a2 * b)) {
        assertEquals(-1, f.compareTo(f2));
      }
      else {
        assertEquals(0, f.compareTo(f2));
      }
    }
  }

  @Test
  public void testAdd() {
    FractionImpl f, f2;
    Random r = new Random();
    int n = 1000000; //这个是正数分母范围

    for (int i = 0; i < 10000; i++) {
      int a = r.nextInt();
      int b = r.nextInt(n) + 1; //避免出现0
      int a2 = r.nextInt();
      int b2 = r.nextInt(n) + 1;
      f = new FractionImpl(a, b);
      f2 = new FractionImpl(a2, b2);

      System.out.println(a);
      System.out.println(a2);
      System.out.println(b);
      System.out.println(b2);
      System.out.println(f.toString() + f2.toString());

      System.out.println("b*b2= " + b * b2);

      Fraction exceptSum = new FractionImpl((a * b2 + a2 * b), b * b2);

      System.out.println(exceptSum.toString());

      System.out.println(f.add(f2).toString());
      assertEquals(exceptSum.toString(), f.add(f2).toString());
    }
  }




}









/*
  @Test
  public void setNumerator() {

  }

  @Test
  public void setDenominator() {
  }

  @Test
  public void toDouble() {
  }

  @Test
  public void testToString() {
  }

  @Test
  public void reciprocal() {
  }

  @Test
  public void add() {
  }

  @Test
  public void compareTo() {
  }
}

 */