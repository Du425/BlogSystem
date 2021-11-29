package com.du.blog;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.sql.SQLException;
import java.util.Collections;
public class CodeGenerator {
    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:33060/blog",
            "root",
            "DST773344");


    /**
     * 执行 run
     */
    public static void main(String[] args) throws SQLException {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig(builder ->
                        builder.author("Du425")
                                .fileOverride()
                                .enableSwagger()
                                .dateType(DateType.TIME_PACK)
                                .commentDate("yyyy-MM-dd")
                                .outputDir("D://java-code/Blog/src/main/java"))
                // 包配置
                .packageConfig(builder ->
                        builder.parent("com.du.blog")
                                .service("service")
                                .serviceImpl("service.impl")
                                .mapper("mapper")
                                .controller("controller")
                                .other("other")
                                .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://java-code/Blog/src/main/resources/mapper")))
                // 策略配置
                .strategyConfig(builder -> builder.addInclude("m_blog","m_user"))

//

                .execute();
    }
}
