public class FactorialImpl implements Factorial {

  @Override
  public int of(int base) {
    return base != 0
        ? base * of(base - 1)
        : 1;
  }
}
