package lotto.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.model.Lotto;
import lotto.model.Prize;
import lotto.model.WinPrizes;

public class SearchPrize {

	public static WinPrizes drawWinPrize(List<Lotto> lottos, List<Integer> lastWinningLottos) {
		Map<Prize, Integer> winPrizes = Arrays.stream(Prize.values())
			.collect(Collectors.toMap(winPrize -> winPrize, winnerResult -> 0, (a, b) -> b));
		for (Lotto lotto : lottos) {
			Prize winPrize = Prize.getWinnersStatus(
				(int)lotto.getLotto().stream().filter(lastWinningLottos::contains).count());
			winPrizes.put(winPrize, winPrizes.get(winPrize) + 1);
		}
		return new WinPrizes(winPrizes);
	}

}
