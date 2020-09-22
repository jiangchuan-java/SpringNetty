import com.ifeng.fhh.fhhService.model.domain.AccountScoreRecord;
import com.ifeng.fhh.zmt.tools.JackSonUtils;

/**
 * @Des:
 * @Author: jiangchuan
 * <p>
 * @Date: 20-9-8
 */
public class Test_01 {

    public static void main(String[] args) {
        AccountScoreRecord scoreRecord = new AccountScoreRecord();
        scoreRecord.seteAccountId(370804l);
        scoreRecord.setDesc("测试");
        scoreRecord.setNegativeScore(30.0);

        System.out.println(JackSonUtils.bean2JsonWithoutError(scoreRecord));
    }
}
