/*
 *
 * Copyright (c) 2005 The Exceoon Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Exceoon Software Foundation (http://www.exceoon.com/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. Products derived from this software may not be called " Exceoon ",
 *    nor may " Exceoon " appear in their name, without prior written
 *    permission of the Exceoon Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * Exceoon Software Corporation and was originally based on software
 * Exceoon copyright (c) 2005, International
 * Business Machines, Inc., http://www.Exceoon.com.  For more
 * information on the Exceoon Software Foundation, please see
 * <http://www.Exceoon.com/>.
 */

package com.google.code.formula4j.impl;

import java.util.List;

import com.google.code.formula4j.type.Arguments;
import com.google.code.formula4j.type.Formula;
import com.google.code.formula4j.type.MathematicalValue;

/**
 * Author	David.Liu 
 * Mail		david.liu@exceoon.com
 * copyright	Exceoon corporation
 */

public class FormulaImpl implements Formula
{
	private String formula;
	
	public FormulaImpl(String formula)
	{
		this.formula = formula;
	}

	@Override
    public MathematicalValue eval(Arguments arguments)
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public List<String> getArgumentNames()
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public String getName()
    {
	    // TODO Auto-generated method stub
	    return null;
    }
	
	private void doParse()
	{
		FormulaMode formulaMode = new FormulaMode();
		
		int currentPos = 0;
		int startPos = 0;
		
		System.out.println("Parse function name ");
		if (Utils.checkEnd(formula,0) && Utils.LETTER.indexOf(formula.charAt(0)) != -1) {
			currentPos ++;
			while (Utils.checkEnd(formula,currentPos) && Utils.VARIBLE.indexOf(formula.charAt(currentPos)) != -1) {
				currentPos ++;
			}
		}
		
		if (currentPos == startPos) {		
			throw Utils.createParseException(formula, currentPos);
		}
		
		String functionName = formula.substring(startPos,currentPos);		
		formulaMode.setName(functionName);
		
		String parameterName;
		
		System.out.println("Parse parameter name ");
		if (Utils.checkEnd(formula,currentPos) &&
				formula.charAt(currentPos) == '(') {
			
			currentPos ++ ;
			startPos = currentPos;
			
			if (Utils.checkEnd(formula,0) && Utils.LETTER.indexOf(formula.charAt(currentPos)) != -1) {				
				currentPos ++;
					
				while (Utils.checkEnd(formula,currentPos) && Utils.VARIBLE.indexOf(formula.charAt(currentPos)) != -1) {
					currentPos ++;
				}
				
				if (currentPos == startPos) {		
					throw Utils.createParseException(formula, currentPos);
				}
				
				parameterName = formula.substring(startPos,currentPos);					
				formulaMode.addArgumentName(parameterName);
				
				while(Utils.checkEnd(formula,currentPos) && formula.charAt(currentPos) == ',')
				{
					currentPos ++;	
					startPos = currentPos;
					
					if (Utils.checkEnd(formula,0) && Utils.LETTER.indexOf(formula.charAt(currentPos)) != -1) {						
						currentPos ++;
						
						while (Utils.checkEnd(formula,currentPos) && Utils.VARIBLE.indexOf(formula.charAt(currentPos)) != -1) {
							currentPos ++;
						}
						
						parameterName = formula.substring(startPos,currentPos);							
						formulaMode.addArgumentName(parameterName);
					}
					
					if (currentPos == startPos) {		
						throw Utils.createParseException(formula, currentPos);
					}
				}
			}
		}
		else
		{
			throw Utils.createParseException(formula, currentPos);
		}
		
		if(!(Utils.checkEnd(formula,currentPos) &&
				formula.charAt(currentPos) == ')'))
		{
			throw Utils.createParseException(formula, currentPos);
		}
		
		currentPos ++;
		
		if(Utils.checkEnd(formula, currentPos) &&
				formula.charAt(currentPos) == '=')
		{
			currentPos ++;
			
		}
		else
		{
			throw Utils.createParseException(formula, currentPos);
		}
		
		System.out.println(formulaMode.toString());
	}
	
	public static void main(String[] args)
	{
		String formula = "sin(x,y,z_1)=1111";
		FormulaImpl impl = new FormulaImpl(formula);
		try
		{
			impl.doParse();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
