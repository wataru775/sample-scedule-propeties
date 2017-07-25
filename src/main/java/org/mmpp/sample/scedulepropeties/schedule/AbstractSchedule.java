package org.mmpp.sample.scedulepropeties.schedule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * スケジュール実行の抽象クラス
 */
public abstract class AbstractSchedule {
    /**
     * ロガー
     */
    private Logger logger = LoggerFactory.getLogger(AbstractSchedule.class);

    @Autowired
    protected Environment environment;

    /**
     * サービスプロファイルを取得します
     * @return サービスプロファイル
     */
    abstract protected String getPrefix();

    /**
     * スケジュール実行
     */
    abstract public void start();

    /**
     * 実行 SQLファイルパスを取得します
     * @return SQLファイルパス
     * @throws NullPointerException ない場合
     */
    private String getQueryFile() throws NullPointerException{

        if(getPrefix() == null) {
            throw new NullPointerException("getPrefixに値が指定していません");
        }
        String prefix = environment.getProperty(getPrefix() + ".prefix");
        if(prefix == null){
            throw new NullPointerException("環境変数" + getPrefix() + ".prefix" + "に値が指定していません");
        }
        String query = environment.getProperty(getPrefix() + ".query");
        if(query == null){
            throw new NullPointerException("環境変数" + getPrefix() + ".query" + "に値が指定していません");
        }
        return "/schedule/" + prefix + "/" + query;
    }

    /**
     * 実行SQLを取得します
     * @return SQL
     * @throws NullPointerException Query Fileがない場合
     */
    public String getQuery(){
        StringBuffer bufQuery = new StringBuffer();
        BufferedReader bufferedReader = null;
        try{
            String filePath = getQueryFile();
            try{
                filePath = getQueryFile();
            }catch (NullPointerException e){
                NullPointerException nullPointerException = new NullPointerException("Query Fileが取得できません");
                nullPointerException.addSuppressed(e);
                throw nullPointerException;
            }
            if(filePath == null){
                throw new NullPointerException("Query Fileが取得できません");
            }
            bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)));

            String line;
            while((line = bufferedReader.readLine()) != null){
                bufQuery.append(line);
            }
            return bufQuery.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

}
