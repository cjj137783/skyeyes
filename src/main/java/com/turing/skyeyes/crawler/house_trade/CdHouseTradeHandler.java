package com.turing.skyeyes.crawler.house_trade;

import com.turing.skyeyes.entity.TCdHouseTradeInfo;
import com.turing.skyeyes.mapper.TCdHouseTradeInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/***
 *
 * @author Turing
 * @description 从成都房产局拉取每日房产交易数据
 */

@Slf4j
public class CdHouseTradeHandler implements PageProcessor {

    private TCdHouseTradeInfoMapper tCdHouseTradeInfoMapper;

    public CdHouseTradeHandler(TCdHouseTradeInfoMapper tCdHouseTradeInfoMapper) {
        this.tCdHouseTradeInfoMapper = tCdHouseTradeInfoMapper;
    }

    private Site site = Site.me().setTimeOut(30000);

    @Override
    public void process(Page page) {
        List<String> contents = page.getHtml().xpath("//table[@bgcolor='#BCBCBC']/tbody/tr[@bgcolor='#FFFFFF']/td/text()").all();

        String mainCityNewHouseTradeNum = StringUtils.isBlank(contents.get(2)) ? "0" : StringUtils.trim(contents.get(2));
        String mainCityNewHouseTradeArea = StringUtils.isBlank(contents.get(3)) ? "0" : StringUtils.trim(contents.get(3));
        String countryNewHouseTradeNum = StringUtils.isBlank(contents.get(7)) ? "0" : StringUtils.trim(contents.get(7));
        String countryNewHouseTradeArea = StringUtils.isBlank(contents.get(8)) ? "0" : StringUtils.trim(contents.get(8));


        String mainCitySecondHouseTradeNum = StringUtils.isBlank(contents.get(17)) ? "0" : StringUtils.trim(contents.get(17));
        String mainCitySecondHouseTradeArea = StringUtils.isBlank(contents.get(18)) ? "0" : StringUtils.trim(contents.get(18));
        String countrySecondHouseTradeNum = StringUtils.isBlank(contents.get(22)) ? "0" : StringUtils.trim(contents.get(22));
        String countrySecondHouseTradeArea = StringUtils.isBlank(contents.get(23)) ? "0" : StringUtils.trim(contents.get(23));

        log.info("主城区新房交易套数:{}，主城区新房交易面积:{}", mainCityNewHouseTradeNum, mainCityNewHouseTradeArea);
        log.info("郊区新房交易套数:{}，郊区新房交易面积:{}", countryNewHouseTradeNum, countryNewHouseTradeArea);
        log.info("主城区二手房交易套数:{}，主城区二手房交易面积:{}", mainCitySecondHouseTradeNum, mainCitySecondHouseTradeArea);
        log.info("郊区二手房交易套数:{}，郊区二手房交易面积:{}", countrySecondHouseTradeNum, countrySecondHouseTradeArea);

        TCdHouseTradeInfo tCdHouseTradeInfo = TCdHouseTradeInfo.builder()
                .urbanNewHouseNum(Integer.parseInt(mainCityNewHouseTradeNum))
                .urbanNewHouseAre(new BigDecimal(mainCityNewHouseTradeArea))
                .countryNewHouseNum(Integer.parseInt(countryNewHouseTradeNum))
                .countryNewHouseArea(new BigDecimal(countryNewHouseTradeArea))
                .urbanOldHouseNum(Integer.parseInt(mainCitySecondHouseTradeNum))
                .urbanOldHouseAre(new BigDecimal(mainCitySecondHouseTradeArea))
                .countryOldHouseNum(Integer.parseInt(countrySecondHouseTradeNum))
                .countryOldHouseAre(new BigDecimal(countrySecondHouseTradeArea))
                .dataDate(new Date())
                .build();
        tCdHouseTradeInfoMapper.insert(tCdHouseTradeInfo);
    }

    @Override
    public Site getSite() {
        return site;
    }
}
