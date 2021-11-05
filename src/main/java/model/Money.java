package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
	private static final String NUMBER_REGEX = "^[0-9]+$";

	private BigDecimal value;

	private Money(int value) {
		this.value = BigDecimal.valueOf(value);
	}

	public static boolean validate(String moneyString) {
		return moneyString.matches(NUMBER_REGEX)
			&& Integer.parseInt(moneyString) >= Lotto.COST;
	}

	public static Money of(Integer money) {
		return new Money(money);
	}

	public static Money of(String moneyString) {
		try {
			return new Money(Integer.parseInt(moneyString));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("숫자만 입력해야 합니다.");
		}
	}

	public PurchaseCount purchaseableCount(Money price) {
		return new PurchaseCount(this.value.intValue() / price.getValue().intValue());
	}

	public BigDecimal getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Money money = (Money)o;
		return Objects.equals(value, money.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
