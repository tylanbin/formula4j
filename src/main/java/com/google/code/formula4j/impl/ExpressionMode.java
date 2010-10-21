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

import com.google.code.formula4j.type.MathematicalValue;

/**
 * Author	David.Liu 
 * Mail		david.liu@exceoon.com
 * copyright	Exceoon corporation
 */

public class ExpressionMode
{
	public static final int TYPE_OPERATOR_NOT_DEFINE = 0;
	public static final int TYPE_VALUE               = 1;
	public static final int TYPE_BINARY_OPERATOR     = 2;
	public static final int TYPE_UNARY_OPERATOR      = 3;
	public static final int TYPE_EXPRESSION_ELEMENT  = 4;
	
	int elementType;
	String expressionName;
	String stringElement;
	Object expressionElementProperties;
	MathematicalValue value;
	ExpressionMode left;
	ExpressionMode right;
	
	public  ExpressionMode(int type, ExpressionMode left, ExpressionMode right, MathematicalValue value, String name, String stringElement, Object  expressionElementProperties)
	{
		this.elementType = type;
		this.left = left;
		this.right = right;
		this.value = value;
		this.stringElement = stringElement;
		this.expressionElementProperties = expressionElementProperties;
		this.expressionName = name;
	}

	/**
     * @return the elementType
     */
    public int getElementType()
    {
    	return elementType;
    }

	/**
     * @param elementType the elementType to set
     */
    public void setElementType(int elementType)
    {
    	this.elementType = elementType;
    }

	/**
     * @return the expressionName
     */
    public String getExpressionName()
    {
    	return expressionName;
    }

	/**
     * @param expressionName the expressionName to set
     */
    public void setExpressionName(String expressionName)
    {
    	this.expressionName = expressionName;
    }

	/**
     * @return the stringElement
     */
    public String getStringElement()
    {
    	return stringElement;
    }

	/**
     * @param stringElement the stringElement to set
     */
    public void setStringElement(String stringElement)
    {
    	this.stringElement = stringElement;
    }

	/**
     * @return the expressionElementProperties
     */
    public Object getExpressionElementProperties()
    {
    	return expressionElementProperties;
    }

	/**
     * @param expressionElementProperties the expressionElementProperties to set
     */
    public void setExpressionElementProperties(Object expressionElementProperties)
    {
    	this.expressionElementProperties = expressionElementProperties;
    }

	/**
     * @return the value
     */
    public MathematicalValue getValue()
    {
    	return value;
    }

	/**
     * @param value the value to set
     */
    public void setValue(MathematicalValue value)
    {
    	this.value = value;
    }

	/**
     * @return the left
     */
    public ExpressionMode getLeft()
    {
    	return left;
    }

	/**
     * @param left the left to set
     */
    public void setLeft(ExpressionMode left)
    {
    	this.left = left;
    }

	/**
     * @return the right
     */
    public ExpressionMode getRight()
    {
    	return right;
    }

	/**
     * @param right the right to set
     */
    public void setRight(ExpressionMode right)
    {
    	this.right = right;
    }
	
	
}
