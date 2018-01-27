package com.linpeng.aengine.api.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import com.linpeng.aengine.api.IAengineApi;

import com.linpeng.aengine.common.Constants;
import com.linpeng.aengine.model.Disease;
import com.linpeng.aengine.model.Principle;
import com.linpeng.aengine.model.PrincipleItem;
import gl.linpeng.analyzer.core.IKSegmenter;
import gl.linpeng.analyzer.core.Lexeme;
import org.apache.lucene.util.CollectionUtil;
import org.beetl.sql.core.db.KeyHolder;

import javax.inject.Inject;

/**
 * 引擎API实现类
 *
 * @author linpeng
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

    @Inject
    private Disease.Mapper diseaseMapper;
    @Inject
    private Principle.Mapper principleMapper;
    @Inject
    private PrincipleItem.Mapper principleItemMapper;

    @Override
    public boolean readContent(String content) {
        byte[] bt = content.getBytes();
        InputStream ip = new ByteArrayInputStream(bt);
        Reader read = new InputStreamReader(ip);
        IKSegmenter iks = new IKSegmenter(read, true);
        // StringBuffer sb = new StringBuffer();
        Lexeme t;
        Lexeme pre = null;
        Lexeme preDisease = null;
        Map<String, List<PrincipleItem>> stashMap = new HashMap<>();
        try {
            while ((t = iks.next()) != null) {
                if (!isScopeSupported(t.getLexemeType())) {
                    continue;
                }

                if (t.getLexemeType() == Lexeme.TYPE_DISEASE) {
                    String diseaseName = t.getLexemeText().trim();
                    preDisease = t;
                    if (stashMap.containsKey(diseaseName)) {
                        continue;
                    } else {
                        // is not in map
                        stashMap.put(diseaseName, new ArrayList<PrincipleItem>());
                    }
                } else if (t.getLexemeType() == Lexeme.TYPE_ADVERB) {
                    Lexeme tmpPre = pre;
                    pre = t;
                    if (tmpPre != null && tmpPre.getLexemeType() == Lexeme.TYPE_ADVERB) {
                        continue;
                    }
                } else if (t.getLexemeType() == Lexeme.TYPE_FOOD || t.getLexemeType() == Lexeme.TYPE_NATRIENT) {
                    if (preDisease == null) {
                        continue;
                    }
                    List<PrincipleItem> principleItemList = stashMap.get(preDisease.getLexemeText().trim());

                    //sb.append(pre.getLexemeText() + ":" + pre.getLexemeTypeString() + "->");
                    PrincipleItem principleItem = new PrincipleItem();
                    principleItem.setAdverb(Constants.Adverb.adverbValueOf(pre.getLexemeText()).getValue());
                    if (t.getLexemeType() == Lexeme.TYPE_FOOD) {
                        principleItem.setType(Constants.PrincipleItemType.FOOD.getValue());
                    } else {
                        principleItem.setType(Constants.PrincipleItemType.NATRIENT.getValue());
                    }
                    principleItem.setTarget(t.getLexemeText());
                    principleItemList.add(principleItem);
                }
                // sb.append(t.getLexemeText() + ":" + t.getLexemeTypeString() + " , ");

            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //sb.delete(sb.length() - 1, sb.length());
        // System.out.println(sb.toString());
        List<Principle> principleList = new ArrayList<>();
        for (Map.Entry<String, List<PrincipleItem>> entry : stashMap.entrySet()) {
            Disease query = new Disease(entry.getKey());
            List<Disease> diseaseList = diseaseMapper.template(query);
            Disease disease = null;
            if (diseaseList != null && diseaseList.size() > 0) {
                disease = diseaseList.get(0);
            } else {
                disease = new Disease(entry.getKey());
                KeyHolder keyHolder = diseaseMapper.insertReturnKey(disease);
                disease.setId(keyHolder.getLong());
            }
            System.out.println(entry.getKey() + "->");

            for (PrincipleItem principleItem : entry.getValue()) {
                System.out.println(" " + principleItem.getAdverb() + ":" + principleItem.getTarget());
                PrincipleItem dbPrincipleItem = principleItemMapper.templateOne(principleItem);
                if (dbPrincipleItem != null) {
                    Long id = principleItemMapper.insertReturnKey(principleItem).getLong();
                    principleItem.setId(id);
                }
                // wrap to principle
                Principle principle = new Principle(disease.getId(), principleItem.getId());
                principleList.add(principle);
            }

        }
        // insert batch
        if (!principleList.isEmpty()) {
            principleMapper.insertBatch(principleList);
        }

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
