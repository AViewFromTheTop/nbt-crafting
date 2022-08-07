package de.siphalor.nbtcrafting.dollar.instruction;

import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.siphalor.nbtcrafting.dollar.DollarEvaluationException;
import de.siphalor.nbtcrafting.dollar.DollarUtil;
import de.siphalor.nbtcrafting.util.NumberUtil;

public class AddInstruction implements BinaryInstruction {
	@Override
	public @Nullable Object apply(@Nullable Object left, @Nullable Object right, @NotNull Function<String, Object> referenceResolver) throws DollarEvaluationException {
		left = assertNotNull(left, 0);
		left = tryResolveReference(left, referenceResolver);
		right = assertNotNull(right, 1);
		right = tryResolveReference(right, referenceResolver);
		if (left instanceof Number) {
			return NumberUtil.sum((Number) left, assertParameterType(right, 1, Number.class));
		} else if (left instanceof String) {
			return left + DollarUtil.asString(right);
		}
		exceptParameterType(left, 0, Number.class, String.class);
		return null; // unreachable
	}

	@Override
	public int getPrecedence() {
		return 40;
	}

}