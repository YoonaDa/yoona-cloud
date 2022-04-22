package com.yoona.cloud.common.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yoonada
 * @email: m15602498163@163.com
 * @create: 2022-03-27 18:24
 * @description: 代码生成器工具类
 */
public class CodeGeneratorUtil {

    /**
     * 父类实体的位置
     */
    public static final String SUPER_ENTITY_CLASS = "com.yoona.cloud.common.entity.BaseEntity";

    public static final String AUTHOR = "yoonada";

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static final String JDBC_URL = "jdbc:mysql://150.158.78.9:3307/yoona-cloud?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";

    public static final String JDBC_USER_NAME = "root";

    public static final String JDBC_PASSWORD = "DD123456aa";

    public static final String PARENT_PACKAGE = "com.yoona.cloud.auth.server";

    public static final String OUTPUT_DIR = "E:\\projects\\mine\\github\\yoona-cloud\\yoona-cloud-auth-server";

    public static final String[] TABLE_PREFIX = new String[]{"t_", "sys_"};

    public static final String[] INCLUDE_TABLES = new String[]{"sys_menu", "sys_role_menu"};

    public static void main(String[] args) {
        GenerateConfig config = new GenerateConfig();
        config.setAuthor(AUTHOR);
        config.setDbType(DbType.MYSQL);
        config.setJdbcDriver(JDBC_DRIVER);
        config.setJdbcUrl(JDBC_URL);
        config.setJdbcUserName(JDBC_USER_NAME);
        config.setJdbcPassword(JDBC_PASSWORD);
        config.setTablePrefix(TABLE_PREFIX);
        config.setParentPackage(PARENT_PACKAGE);
        config.setOutputDir(OUTPUT_DIR);
        config.setIncludeTables(INCLUDE_TABLES);
        execute(config);
    }

    public static void execute(GenerateConfig generateConfig) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generateConfig.getOutputDir() + "/src/main/java");
        gc.setFileOverride(true);
        //ActiveRecord特性
        gc.setActiveRecord(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(true);
        gc.setEnableCache(false);
        // 自动打开输出目录
        gc.setOpen(false);
        gc.setAuthor(generateConfig.getAuthor());
        gc.setSwagger2(true);
        //主键策略
        gc.setIdType(IdType.AUTO);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(generateConfig.getDbType());
        dsc.setDriverName(generateConfig.getJdbcDriver());
        dsc.setUrl(generateConfig.getJdbcUrl());
        dsc.setUsername(generateConfig.getJdbcUserName());
        dsc.setPassword(generateConfig.getJdbcPassword());
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false);
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        strategy.setEntityTableFieldAnnotationEnable(false);
        // 此处可以移除表前缀表前缀
        strategy.setTablePrefix(generateConfig.getTablePrefix());
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 字段生成策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 父类实体
        strategy.setSuperEntityClass(SUPER_ENTITY_CLASS);
        // 父类实体字段
        strategy.setSuperEntityColumns("create_time", "update_time", "is_delete", "CREATE_TIME", "UPDATE_TIME", "IS_DELETE");
        // mapper 父类
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        // 接口父类
        strategy.setSuperServiceClass("com.baomidou.mybatisplus.extension.service.IService");
        // 接口实现类父类
        strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        // 需要生成的表
        strategy.setInclude(generateConfig.getIncludeTables());

        TemplateConfig templateConfig = new TemplateConfig();
        //不生成controller
        templateConfig.setController(null);
        // 公共字段填充
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(new TableFill("create_time", FieldFill.INSERT));
        tableFills.add(new TableFill("update_time", FieldFill.UPDATE));
        tableFills.add(new TableFill("CREATE_TIME", FieldFill.INSERT));
        tableFills.add(new TableFill("UPDATE_TIME", FieldFill.UPDATE));
        strategy.setTableFillList(tableFills);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包名
        pc.setParent(generateConfig.getParentPackage());
        //父包模块名
        pc.setModuleName(generateConfig.getModuleName());
        //实体类父包
        pc.setEntity("entity");
        //controller父包
        pc.setController("controller");
        //mapper父包
        pc.setMapper("mapper");
        //xml父包
        pc.setXml("");
        pc.setServiceImpl("service.impl");
        pc.setService("service");
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generateConfig.getOutputDir() + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
    }

}