package crml.translator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import crml.translator.Signature.Type;

public class OperatorMapping {
	
	public static HashMap<String, List<Signature>> get_operator_map (){
		HashMap<String, List<Signature>> built_in_operators = new HashMap<String, List<Signature>>();
		
		// table for mapping CRML built in operators to library functions
		
		//TODO add full operator list
		
		//TODO add sets
		
		// Integer signatures
		List<String> int1 = Arrays.asList("Integer");
		List<String> int2 = Arrays.asList("Integer", "Integer");
		
		
		// Real signatures
		List<String> real1 = Arrays.asList("Real");
		List<String> real2 = Arrays.asList("Real", "Real");
		
		// Real + Int mix and match
		List<String> intreal = Arrays.asList("Integer", "Real");
		List<String> realint = Arrays.asList("Real", "Integer");
		
		// Boolean signatures
		List<String> bool1 = Arrays.asList("Boolean");
		List<String> bool2 = Arrays.asList("Boolean", "Boolean");
		
		// String signatures
		List<String> string2 = Arrays.asList("String", "String");
		
		// Period mix and match signatures
		List<String> periodReal = Arrays.asList("Period", "Real");
		List<String> realPeriod = Arrays.asList("Real", "Period");
		
		// Default operand names in built-in functions
		List<String> params = Arrays.asList("r1", "r2");
		
		// SetLists
		List<Boolean> setOnset = Arrays.asList(true, true);
		List<Boolean> setUnary = Arrays.asList(true);
		List<Boolean> setOnvar = Arrays.asList(true, false);
		List<Boolean> varOnset = Arrays.asList(false, true);
		
		
		
		// + operators
		List<Signature> plus_sigs = 
				Arrays.asList(new Signature("+", int2, "Integer", Type.OPERATOR),
						new Signature(" ", int1, "Integer", Type.OPERATOR),
						new Signature("+", real2, "Real", Type.OPERATOR),
						new Signature(" ", real1, "Real", Type.OPERATOR),
						new Signature("+", string2, "String", Type.OPERATOR),
						new Signature("CRMLtoModelica.Functions.add4", bool2, params, "Boolean", Type.FUNCTION));
		
		
		
		built_in_operators.put("+", plus_sigs);
		
		// - operators
				List<Signature> minus_sigs = 
						Arrays.asList(new Signature("-", int2, "Integer", Type.OPERATOR),
								new Signature("-", int1, "Integer", Type.OPERATOR),
								new Signature("-", real1, "Real", Type.OPERATOR),
								new Signature("-", real2, "Real", Type.OPERATOR),
								new Signature("CRMLtoModelica.Functions.diff4", bool2, params, "Boolean", Type.FUNCTION));
						
		built_in_operators.put("-", minus_sigs);
		
		// * operators
		List<Signature> mult_sigs = 
				Arrays.asList(new Signature("*", int2, "Integer", Type.OPERATOR),
						new Signature("*", real2, "Real", Type.OPERATOR),
						new Signature("CRMLtoModelica.Functions.mul2x4", bool2, params, "Boolean", Type.FUNCTION));
				
		built_in_operators.put("*", mult_sigs);
		
		// / operators
				List<Signature> div_sigs = 
						Arrays.asList(new Signature("/", int2, "Integer", Type.OPERATOR),
								new Signature("/", real2, "Real", Type.OPERATOR));
						
				built_in_operators.put("/", div_sigs);

		// <= operators
		List<Signature> leq_sigs = 
				Arrays.asList(new Signature("<=", int2,  "Boolean", Type.OPERATOR),
						new Signature("<=", real2, "Boolean", Type.OPERATOR),
						new Signature("<=", intreal, "Boolean", Type.OPERATOR),
						new Signature("<=", realint, "Boolean", Type.OPERATOR),
						new Signature("CRMLtoModelica.realPeriodleq", realPeriod, params, "Boolean", Type.BLOCK),
						new Signature("CRMLtoModelica.Blocks.Logical4.leq", bool2, params, "Boolean", Type.BLOCK));
		
		built_in_operators.put("<=", leq_sigs);
		
		// <= operators
				List<Signature> le_sigs = 
						Arrays.asList(new Signature("<", int2,  "Boolean", Type.OPERATOR),
								new Signature("<", real2, "Boolean", Type.OPERATOR),
								new Signature("<", intreal, "Boolean", Type.OPERATOR),
								new Signature("<", realint, "Boolean", Type.OPERATOR),
								new Signature("leqArray", realint, params, "Boolean", Type.SET_OP, setOnvar, true),
								new Signature("CRMLtoModelica.realPeriodleq", realPeriod, params, "Boolean", Type.BLOCK),
								new Signature("CRMLtoModelica.Blocks.Logical4.leq", bool2, params, "Boolean", Type.BLOCK));
				
				built_in_operators.put("<", le_sigs);
		
		// >= operators
				List<Signature> geq_sigs = 
						Arrays.asList(new Signature(">=", int2,  "Boolean", Type.OPERATOR),
								new Signature(">=", real2, "Boolean", Type.OPERATOR),
								new Signature(">=", intreal, "Boolean", Type.OPERATOR),
								new Signature(">=", realint, "Boolean", Type.OPERATOR),
								new Signature("CRMLtoModelica.Blocks.Logical4.geq", bool2, params, "Boolean", Type.FUNCTION));
				
				built_in_operators.put(">=", geq_sigs);
				
				// >= operators
				List<Signature> gr_sigs = 
						Arrays.asList(new Signature(">", int2,  "Boolean", Type.OPERATOR),
								new Signature(">", real2, "Boolean", Type.OPERATOR),
								new Signature(">", intreal, "Boolean", Type.OPERATOR),
								new Signature(">", realint, "Boolean", Type.OPERATOR),
								new Signature("CRMLtoModelica.Functions.geq", bool2, params, "Boolean", Type.FUNCTION));
				
				built_in_operators.put(">", gr_sigs);

		
		// and operators	
		List<Signature> and_sigs = 
						Arrays.asList(new Signature("CRMLtoModelica.Functions.and4", bool2, params, "Boolean", Type.FUNCTION),
								new Signature("arrayAnd", bool1, params, "Boolean", Type.SET_OP, setUnary, false));
		built_in_operators.put("and", and_sigs);
		
		// not operators		
		List<Signature> not_sigs = 
				Arrays.asList(new Signature("CRMLtoModelica.Functions.not4", bool1, params, "Boolean", Type.FUNCTION),
						new Signature("arrayNot", bool1, params, "Boolean", Type.SET_OP, setUnary, false));
		built_in_operators.put("not", not_sigs);
		
		
		// end operators	TODO proper implementation	
				built_in_operators.put("end", 
						Arrays.asList(new Signature("CRMLtoModelica.endP", Arrays.asList("Period"), params, "Real", Type.BLOCK)));
				built_in_operators.put("start", 
						Arrays.asList(new Signature("CRMLtoModelica.startP", Arrays.asList("Period"), params, "Real", Type.BLOCK)));
		
		// filter operator
				built_in_operators.put("filter", 
						Arrays.asList(new Signature("CRMLtoModelica.CRML.filterC", Arrays.asList("Clock", "Boolean"), params, "Clock", Type.BLOCK)));
		
		// card operator
				built_in_operators.put("card", 
						Arrays.asList(new Signature("CRMLtoModelica.cardClock", Arrays.asList("Clock"), params, "Integer", Type.BLOCK)));
		
		// CONSTRUCTORS TODO finalize constructor table
				
		// String
		List<Signature> string_sigs = 
				Arrays.asList(new Signature("intToString", int1, params, "String", Type.FUNCTION),
						new Signature("realToString", real1, params, "String", Type.FUNCTION));
		
				built_in_operators.put("String", string_sigs);
				
	
				
		return built_in_operators;
	}
	
	public static Signature is_defined (HashMap<String, List<Signature>> operator_map, String op, String type, Boolean isSet) {
		
		List<Signature> sigs = operator_map.get(op);
		
		if(sigs==null)
			return null;
		
		for (Signature s:sigs) {
			if(s.variable_types.size()==1 && s.variable_types.get(0).equals(type) && s.variable_is_set.get(0) == isSet)
				return s;
		}
		
		return null;
	}

	public static Signature is_defined(HashMap<String, List<Signature>> operator_map, String op, String type1,
			String type2, Boolean isSet1, Boolean isSet2) {
		List<Signature> sigs = operator_map.get(op);
		
		if(sigs==null)
			return null;
		
		for (Signature s:sigs) {
			if(s.variable_types.size()==2 && s.variable_types.get(0).equals(type1) && s.variable_types.get(1).equals(type2)
					&& s.variable_is_set.get(0) == isSet1 && s.variable_is_set.get(1) == isSet2)
				return s;
		}
		
		return null;
	}

}
