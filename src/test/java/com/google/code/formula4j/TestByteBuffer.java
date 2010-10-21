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

package com.google.code.formula4j;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

import junit.framework.TestCase;

/**
 * Author	David.Liu 
 * Mail		david.liu@exceoon.com
 * copyright	Exceoon corporation
 */

public class TestByteBuffer extends TestCase
{

	@SuppressWarnings("unused")
	public void testGet()
	{
		ByteBuffer b = ByteBuffer.allocate(100);

		printByteBufferPositionInfo(b);
		

		b.put("1234567890".getBytes());
		// b.flip();
		printByteBufferPositionInfo(b);

		byte b1 = b.get(0);
		byte b2 = b.get(1);

		System.out.println(b.get());

		printByteBufferPositionInfo(b);
		byte bytes[] = new byte[100];
		b.position(0);
		b.get(bytes, 0, 10);
		System.out.println(new String(bytes, 0, 10));

		// b.position(b.limit());
		// b.limit(100);
		b.put("abcde".getBytes());
		b.position(10);
		b.get(bytes, 0, 4);
		System.out.println(b.get());
		System.out.println(new String(bytes, 0, 4));

		printByteBufferPositionInfo(b);
	}

	public void testInteger()
	{
		int i = Integer.MAX_VALUE;

		i++;

		System.out.println(i);
	}

	public void testPut()
	{
		ByteBuffer b = ByteBuffer.allocate(100);
		printByteBufferPositionInfo(b);
		b.put("1234567890".getBytes());
		printByteBufferPositionInfo(b);

		ByteBuffer b2 = ByteBuffer.allocate(100);
		b2.put(b);

		printByteBufferPositionInfo(b2);
	}

	public void testLimit()
	{
		ByteBuffer b = ByteBuffer.allocate(100);
		b.put("1234567890".getBytes());
		b.flip();
		printByteBufferPositionInfo(b);

		int olimit = b.limit();

		printByteBufferPositionInfo(b);
		b.limit(4);
		printByteBufferPositionInfo(b);
		b.limit(olimit);
		printByteBufferPositionInfo(b);

	}

	public void testCompact()
	{
		ByteBuffer b = ByteBuffer.allocate(16);
		printByteBufferPositionInfo(b);
		b.flip();
		printByteBufferPositionInfo(b);
		b.clear();

		byte[] a =
		{ 1, 2, 3, 4, 5, 6, 7, 8 };

		b.put(a);
		printByteBufferPositionInfo(b);
		a = new byte[]
		{ 1, 2, 3, 4, 5 };
		b.put(a);
		printByteBufferPositionInfo(b);

		b.compact();
		printByteBufferPositionInfo(b);

		b.put(a);
		printByteBufferPositionInfo(b);

		b.compact();
		printByteBufferPositionInfo(b);
	}

	private void printByteBufferPositionInfo(ByteBuffer b)
	{
		System.out.println("==========================");
		System.out.println("position : " + b.position());
		System.out.println("remain : " + b.remaining());
		System.out.println("lenght : " + b.limit());
	}

	public void testBitOp()
	{
		bitOp(129);
		bitOp(1);
	}

	@SuppressWarnings("unused")
	public void testBit()
	{
		int n = 953;
		byte a = (byte) (n & 0xff >> 8);
		byte b = (byte) n;

		System.out.println((n & 0xff >> 8));
	}

	public void bitOp(int clientId)
	{
		byte[] b = int2bytes(clientId);

		int v = b[0] & 0xff << 24 | b[1] & 0xff << 16 | b[2] & 0xff
		        | (byte) b[3] & 0xff;

		System.out.println(b[0] + "," + b[1] + "," + b[2] + "," + b[3] + " = "
		        + v);
	}

	public void testConcurrentLinkedQueue()
	{
		ConcurrentLinkedQueue<Object> q = new ConcurrentLinkedQueue<Object>();

		Object test = new Object();

		q.add(test);
		q.add(test);

		q.poll();
		q.poll();

		System.out.println(q.size());

	}

	public static int bytes2int(byte b[])
	{
		return b[3] & 0xff | (b[2] & 0xff) << 8 | (b[1] & 0xff) << 16
		        | (b[0] & 0xff) << 24;
	}

	public static byte[] int2bytes(int n)
	{
		byte b[] = new byte[4];
		b[0] = (byte) (n >> 24);
		b[1] = (byte) (n >> 16);
		b[2] = (byte) (n >> 8);
		b[3] = (byte) n;
		return b;
	}

	@SuppressWarnings("finally")
	public void testException()
	{
		try
		{
			try
			{
				throw new Exception("test");
			} finally
			{
				return;
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage() + "=========");
			e.printStackTrace();
		}
	}
}
