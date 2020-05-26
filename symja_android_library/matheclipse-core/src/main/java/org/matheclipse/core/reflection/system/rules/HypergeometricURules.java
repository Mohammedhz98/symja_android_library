package org.matheclipse.core.reflection.system.rules;

import static org.matheclipse.core.expression.F.*;
import org.matheclipse.core.interfaces.IAST;

/**
 * <p>Generated by <code>org.matheclipse.core.preprocessor.RulePreprocessor</code>.</p>
 * <p>See GIT repository at: <a href="https://github.com/axkr/symja_android_library">github.com/axkr/symja_android_library under the tools directory</a>.</p>
 */
public interface HypergeometricURules {
  /**
   * <ul>
   * <li>index 0 - number of equal rules in <code>RULES</code></li>
	 * </ul>
	 */
  final public static int[] SIZES = { 0, 2 };

  final public static IAST RULES = List(
    IInit(HypergeometricU, SIZES),
    // HypergeometricU(1,m_,z_):=E^z*z^(1-m)*Gamma(-1+m,z)
    ISetDelayed(HypergeometricU(C1,m_,z_),
      Times(Exp(z),Power(z,Subtract(C1,m)),Gamma(Plus(CN1,m),z))),
    // HypergeometricU(1/2,1,z_):=(E^(z/2)*BesselK(0,z/2))/Sqrt(Pi)
    ISetDelayed(HypergeometricU(C1D2,C1,z_),
      Times(Exp(Times(C1D2,z)),Power(Pi,CN1D2),BesselK(C0,Times(C1D2,z)))),
    // HypergeometricU(n_Integer,b_,z_):=-1/Pochhammer(2-b,-1+n)*(-Gamma(-1+b,z)*z^(1-b)*E^z*LaguerreL(-1+n,1-b,-z)+Sum((LaguerreL(-1-k+n,-b+k+1,-z)*LaguerreL(-1+k,-1+b-k,z))/k,{k,1,-1+n}))/;n>0
    ISetDelayed(HypergeometricU($p(n, Integer),b_,z_),
      Condition(Times(CN1,Power(Pochhammer(Subtract(C2,b),Plus(CN1,n)),CN1),Plus(Times(CN1,Gamma(Plus(CN1,b),z),Power(z,Subtract(C1,b)),Exp(z),LaguerreL(Plus(CN1,n),Subtract(C1,b),Negate(z))),Sum(Times(Power(k,CN1),LaguerreL(Plus(CN1,Negate(k),n),Plus(Negate(b),k,C1),Negate(z)),LaguerreL(Plus(CN1,k),Plus(CN1,b,Negate(k)),z)),List(k,C1,Plus(CN1,n))))),Greater(n,C0)))
  );
}