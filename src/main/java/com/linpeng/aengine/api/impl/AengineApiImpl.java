package com.linpeng.aengine.api.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import com.linpeng.aengine.api.IAengineApi;

import gl.linpeng.analyzer.core.IKSegmenter;
import gl.linpeng.analyzer.core.Lexeme;

/**
 * 引擎API实现类
 * 
 * @author linpeng
 *
 */
public class AengineApiImpl implements IAengineApi {
	static final Set<Integer> supportedScopeSet;

	static {
		supportedScopeSet = new HashSet<>(10);
		supportedScopeSet.add(Lexeme.TYPE_ADVERB);
		supportedScopeSet.add(Lexeme.TYPE_DISEASE);
		supportedScopeSet.add(Lexeme.TYPE_FOOD);
		supportedScopeSet.add(Lexeme.TYPE_NATRIENT);
	}

	@Override
	public boolean readContent(String content) {
		byte[] bt = content.getBytes();
		InputStream ip = new ByteArrayInputStream(bt);
		Reader read = new InputStreamReader(ip);
		IKSegmenter iks = new IKSegmenter(read, true);
		StringBuffer sb = new StringBuffer();
		Lexeme t;
		Lexeme pre = null;
		try {
			while ((t = iks.next()) != null) {
				if (!isScopeSupported(t.getLexemeType())) {
					continue;
				}

				if (t.getLexemeType() == Lexeme.TYPE_ADVERB) {
					Lexeme tmpPre = pre;
					pre = t;
					if (tmpPre != null && tmpPre.getLexemeType() == Lexeme.TYPE_ADVERB) {
						continue;
					}
				} else if (t.getLexemeType() == Lexeme.TYPE_FOOD || t.getLexemeType() == Lexeme.TYPE_NATRIENT) {
					sb.append(pre.getLexemeText() + ":" + pre.getLexemeTypeString() + "->");
				}
				sb.append(t.getLexemeText() + ":" + t.getLexemeTypeString() + " , ");

			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		sb.delete(sb.length() - 1, sb.length());
		System.out.println(sb.toString());
		return true;
	}

	/**
	 * Determine is lexeme type supported
	 * 
	 * @param lexemeType
	 * @return TRUE if supported
	 */
	private boolean isScopeSupported(int lexemeType) {
		return supportedScopeSet.contains(lexemeType);
	}

}
