package lotto.domain;

import java.util.*;

public class Judgement {
    Buy buy = null;
    Draw draw = null;
    private List<Integer> winningNumber = null;
    private int bonusNumber = 0;
    private List<Lotto> lottoGames = null;
    Map<String, Integer> result = null;

    public Judgement(Buy buy, Draw draw) {
        this.buy = buy;
        this.draw = draw;
        result = new HashMap<>();
        for (Prize prize : Prize.values()) {
            result.put(prize.name(), 0);
        }
    }

    public void correctNumbers() {
        winningNumber = draw.getWinningNumber();
        bonusNumber = draw.getBonusNumber();
        lottoGames = buy.getLottoGames();
        for (Lotto lotto : lottoGames) {
            correctCount(lotto.getNumbers());
        }
        System.out.println(result);
    }

    public void correctCount(List<Integer> lotto) {
        double count = 0;
        for (int i : lotto) {
            if (winningNumber.contains(i)) {
                count++;
            }
        }
        if (count == 5.0 && lotto.contains(bonusNumber)) {
            count = 5.5;
        }
        for (Prize prize : Prize.values()) {
            if (count >= 3.0 && prize.getCount() == count) {
                result.put(prize.name(), result.get(prize.name()) + 1);
            }
        }
    }
}
