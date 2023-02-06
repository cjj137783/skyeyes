package com.turing.skyeyes.crawler.house_trade;


import com.turing.skyeyes.mapper.TCdHouseTradeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class CdHouseTradeSchedualer {

    @Autowired
    private TCdHouseTradeInfoMapper tCdHouseTradeInfoMapper;

    @Scheduled(cron = "0 0 22 * * ?")
    public void startPullCdHouseTradeInfo() {
        Spider.create(new CdHouseTradeHandler(tCdHouseTradeInfoMapper))
                .addUrl("https://www.cdzjryb.com/SCXX/Default.aspx?action=ucEveryday2")
                .thread(1)
                .run();
    }
}
