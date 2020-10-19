package net.htlgkr.feinboeck18.example4;

import java.util.stream.IntStream;

public class GetSumOfUnevenSquares {
    public static void main(String[] args) {
        final int result = IntStream.of(1,2,3,4,5,6,7,8,9)
                .filter((int value) -> value % 2 != 0)
                .map(operand -> (int) Math.pow(operand, 2))
                .reduce(0, Integer::sum);
        System.out.println(result);
    }
}