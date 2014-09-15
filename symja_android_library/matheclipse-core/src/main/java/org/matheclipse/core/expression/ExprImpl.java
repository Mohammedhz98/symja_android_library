package org.matheclipse.core.expression;

import java.util.List;
import java.util.Map;

import org.matheclipse.core.basic.Config;
import org.matheclipse.core.eval.EvalEngine;
import org.matheclipse.core.eval.exception.IterationLimitExceeded;
import org.matheclipse.core.interfaces.IAST;
import org.matheclipse.core.interfaces.IComplex;
import org.matheclipse.core.interfaces.IComplexNum;
import org.matheclipse.core.interfaces.IExpr;
import org.matheclipse.core.interfaces.IFraction;
import org.matheclipse.core.interfaces.IInteger;
import org.matheclipse.core.interfaces.INum;
import org.matheclipse.core.interfaces.INumber;
import org.matheclipse.core.interfaces.IRational;
import org.matheclipse.core.interfaces.ISignedNumber;
import org.matheclipse.core.interfaces.ISymbol;
import org.matheclipse.core.patternmatching.IPatternMatcher;
import org.matheclipse.core.patternmatching.PatternMatcher;
import org.matheclipse.core.visit.VisitorReplaceAll;
import org.matheclipse.core.visit.VisitorReplacePart;
import org.matheclipse.core.visit.VisitorReplaceSlots;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import edu.jas.structure.ElemFactory;

/**
 * Abstract base class for atomic expression objects.
 */
@SuppressWarnings("serial")
public abstract class ExprImpl implements IExpr {

	public static IExpr replaceRepeated(final IExpr expr, VisitorReplaceAll visitor) {
		IExpr result = expr;
		IExpr temp = expr.accept(visitor);
		final int iterationLimit = EvalEngine.get().getIterationLimit();
		int iterationCounter = 1;
		while (temp != null) {
			result = temp;
			temp = result.accept(visitor);
			if (iterationLimit >= 0 && iterationLimit <= ++iterationCounter) {
				IterationLimitExceeded.throwIt(iterationCounter, result);
			}
		}
		return result;
	}

	@Override
	public IExpr abs() {
		if (this instanceof INumber) {
			return ((INumber) this).eabs();
		}
		throw new UnsupportedOperationException(toString());
	}

	@Override
	public IExpr and(final IExpr that) {
		return F.And(this, that);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IExpr apply(IExpr... leaves) {
		final IAST ast = F.ast(head());
		for (int i = 0; i < leaves.length; i++) {
			ast.add(leaves[i]);
		}
		return ast;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IExpr apply(List<? extends IExpr> leaves) {
		final IAST ast = F.ast(head());
		for (int i = 0; i < leaves.size(); i++) {
			ast.add(leaves.get(i));
		}
		return ast;
	}

	@Override
	public Object asType(Class clazz) {
		if (clazz.equals(Boolean.class)) {
			if (this.equals(F.True)) {
				return Boolean.TRUE;
			}
			if (this.equals(F.False)) {
				return Boolean.FALSE;
			}
		} else if (clazz.equals(Integer.class)) {
			if (isSignedNumber()) {
				try {
					return Integer.valueOf(((ISignedNumber) this).toInt());
				} catch (final ArithmeticException e) {
				}
			}
		} else if (clazz.equals(java.math.BigInteger.class)) {
			if (this instanceof IntegerSym) {
				return new java.math.BigInteger(((IntegerSym) this).toByteArray());
			}
		} else if (clazz.equals(String.class)) {
			return toString();
		}
		throw new UnsupportedOperationException("ExprImpl.asType() - cast not supported.");
	}

	@Override
	public int compareTo(IExpr expr) {
		if (hierarchy() > expr.hierarchy()) {
			return 1;
		}
		if (hierarchy() < expr.hierarchy()) {
			return -1;
		}
		return 0;
	}

	@Override
	public IExpr copy() {
		try {
			return (IExpr) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public IExpr divide(IExpr that) {
		// don't use times(that.inverse()) directly!
		if (that.isNumber()) {
			return F.eval(F.Times(this, that.inverse()));
		}
		return F.eval(F.Times(this, F.Power(that, F.CN1)));
	}

	@Override
	public IExpr[] egcd(IExpr b) {
		throw new UnsupportedOperationException(toString());
	}

	@Override
	public IExpr evaluate(EvalEngine engine) {
		return null;
	}

	@Override
	public ElemFactory<IExpr> factory() {
		throw new UnsupportedOperationException(toString());
	}

	@Override
	public String fullFormString() {
		return toString();
	}

	@Override
	public IExpr gcd(IExpr that) {
		throw new UnsupportedOperationException("gcd(" + toString() + ", " + that.toString() + ")");
		// if (equals(that)) {
		// return that;
		// }
		// return F.C1;
	}

	@Override
	public IExpr getAt(final int index) {
		return F.Part(this, F.integer(index));
	}

	@Override
	public abstract ISymbol head();

	@Override
	public String internalFormString(boolean symbolsAsFactoryMethod, int depth) {
		return toString();
	}

	/** {@inheritDoc} */
	@Override
	public IExpr inverse() {
		return power(F.CN1);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isAnd() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isArcCos() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isArcCosh() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isArcSin() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isArcSinh() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isArcTan() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isArcTanh() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isAST() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isAST(final IExpr header) {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isAST(final IExpr header, final int sz) {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isAST(IExpr header, int length, IExpr... args) {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isAST(final String symbol) {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isAST(final String symbol, final int length) {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isASTSizeGE(final IExpr header, final int length) {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isAtom() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isComplex() {
		return this instanceof IComplex;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isComplexInfinity() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isComplexNumeric() {
		return this instanceof IComplexNum;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isCondition() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isConstant() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isCos() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isCosh() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isDirectedInfinity() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isE() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFalse() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isFlatAST() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFraction() {
		return this instanceof IFraction;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFree(final IExpr pattern) {
		return isFree(pattern, true);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFree(final IExpr pattern, boolean heads) {
		final IPatternMatcher matcher = new PatternMatcher(pattern);
		return !matcher.apply(this);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFree(Predicate<IExpr> predicate, boolean heads) {
		return !predicate.apply(this);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isFunction() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isGEOrdered(final IExpr obj) {
		return compareTo(obj) >= 0;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isGTOrdered(final IExpr obj) {
		return compareTo(obj) > 0;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isInfinity() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isInteger() {
		return this instanceof IInteger;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isLEOrdered(final IExpr obj) {
		return compareTo(obj) <= 0;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isList() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isListOfLists() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isLog() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isLTOrdered(final IExpr obj) {
		return compareTo(obj) < 0;
	}

	/** {@inheritDoc} */
	@Override
	public final int[] isMatrix() {
		// default: no matrix
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isMember(final IExpr pattern, boolean heads) {
		final IPatternMatcher matcher = new PatternMatcher(pattern);
		return isMember(matcher, heads);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isMember(Predicate<IExpr> predicate, boolean heads) {
		return predicate.apply(this);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isMinusOne() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isModule() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNegative() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNegativeInfinity() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNot() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNumber() {
		return this instanceof INumber;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNumEqualInteger(IInteger ii) throws ArithmeticException {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNumeric() {
		return this instanceof INum || this instanceof IComplexNum;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNumericFunction() {
		return isNumber() || isConstant();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNumericMode() {
		return isNumeric();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isNumIntValue() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isOne() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isONE() {
		return isOne();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isOr() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isOrderlessAST() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPattern() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPatternDefault() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPatternExpr() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPatternSequence() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPi() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isPlus() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isPositive() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isPower() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isRational() {
		return this instanceof IRational;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isRealFunction() {
		return isSignedNumber() || isConstant();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isRuleAST() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSame(IExpr expression) {
		return isSame(expression, Config.DOUBLE_EPSILON);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSame(IExpr expression, double epsilon) {
		return equals(expression);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isSequence() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSignedNumber() {
		return this instanceof ISignedNumber;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isSin() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isSinh() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSlot() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSlotSequence() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isSymbol() {
		return this instanceof ISymbol;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isTan() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isTanh() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isTimes() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isTrue() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isUnit() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isValue() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public final int isVector() {
		// default: no vector
		return -1;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isZero() {
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean isZERO() {
		return isZero();
	}

	/** {@inheritDoc} */
	public long leafCount() {
		return isAtom() ? 1L : 0L;
	}

	/** {@inheritDoc} */
	@Override
	public List<IExpr> leaves() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public IExpr minus(final IExpr that) {
		if (this.isNumber() && that.isNumber()) {
			return F.eval(F.Plus(this, ((INumber) that).opposite()));
		}
		if (that.isNumber()) {
			return F.eval(F.Plus(this, ((INumber) that).opposite()));
		}
		return F.eval(F.Plus(this, F.Times(F.CN1, that)));
	}

	@Override
	public IExpr mod(final IExpr that) {
		return F.Mod(this, that);
	}

	/** {@inheritDoc} */
	@Override
	public final IExpr multiply(final IExpr that) {
		return times(that);
	}

	/** {@inheritDoc} */
	@Override
	public IExpr negate() {
		return opposite();
	}

	/** {@inheritDoc} */
	@Override
	public final IExpr negative() {
		return opposite();
	}

	/** {@inheritDoc} */
	public IExpr opposite() {
		return times(F.CN1);
	}

	@Override
	public IExpr or(final IExpr that) {
		return F.Or(this, that);
	}

	@Override
	public IExpr plus(final IExpr that) {
		return F.eval(F.Plus(this, that));
	}

	/** {@inheritDoc} */
	@Override
	public final IExpr power(final IExpr that) {
		if (this.isNumber() && that.isNumber()) {
			return F.eval(F.Power(this, that));
		}
		return F.Power(this, that);
	}

	/** {@inheritDoc} */
	@Override
	public final IExpr power(final int n) {
		if (this.isNumber()) {
			return F.eval(F.Power(this, F.integer(n)));
		}
		return F.Power(this, F.integer(n));
	}

	@Override
	public IExpr remainder(IExpr that) {
		throw new UnsupportedOperationException(toString());
		// if (equals(that)) {
		// return F.C0;
		// }
		// return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IExpr replaceAll(final Function<IExpr, IExpr> function) {
		return this.accept(new VisitorReplaceAll(function));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IExpr replaceAll(final IAST astRules) {
		return this.accept(new VisitorReplaceAll(astRules));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IExpr replacePart(final IAST astRules) {
		return this.accept(new VisitorReplacePart(astRules));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IExpr replaceRepeated(final Function<IExpr, IExpr> function) {
		return replaceRepeated(this, new VisitorReplaceAll(function));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IExpr replaceRepeated(final IAST astRules) {
		return replaceRepeated(this, new VisitorReplaceAll(astRules));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IExpr replaceSlots(final IAST astSlots) {
		return this.accept(new VisitorReplaceSlots(astSlots));
	}

	/**
	 * Signum functionality is used in JAS toString() method, don't use it as math signum function.
	 * 
	 * @deprecated
	 */
	@Deprecated
	@Override
	public int signum() {
		if (isZERO()) {
			return 0;
		}
		if (isSignedNumber()) {
			return ((ISignedNumber) this).sign();
		}
		return 1;
	}

	@Override
	public IExpr subtract(IExpr that) {
		return plus(that.negate());
	}

	@Override
	public IExpr sum(IExpr that) {
		return this.plus(that);
	}

	/** {@inheritDoc} */
	@Override
	public IExpr times(final IExpr that) {
		return F.eval(F.Times(this, that));
	}

	@Override
	public ISymbol topHead() {
		return head();
	}

	@Override
	public String toScript() {
		return toString();
	}

	@Override
	public String toScriptFactory() {
		throw new UnsupportedOperationException(toString());
	}

	@Override
	public IExpr variables2Slots(final Map<IExpr, IExpr> map, final List<IExpr> variableList) {
		return this;
	}

}
