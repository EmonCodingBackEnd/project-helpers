/*
 * 文件名称：JsonSerializer.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190331 19:52
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190331-01         Rushing0711     M201903311952 新建文件
 ********************************************************************************/
package helper.archetype.cloud.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface JsonCustomSerializer {

    class BigDecimal2FormatStringSerializer extends JsonSerializer<BigDecimal> {
        // 默认的格式化字符样式 “#.00”  还可以是像“#.0000”
        private static final String DEFAULT_FORMAT_PATTERN = "#,##0.00";

        private DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_FORMAT_PATTERN);

        @Override
        public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            gen.writeString(decimalFormat.format(value));
        }
    }

    class BigDecimal2SimpleStringSerializer extends JsonSerializer<BigDecimal> {
        // 默认的格式化字符样式 “#.00”  还可以是像“#.0000”
        private static final String DEFAULT_FORMAT_PATTERN = "#.00";

        private DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_FORMAT_PATTERN);

        @Override
        public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            gen.writeString(decimalFormat.format(value));
        }
    }

    class Date2DateStringSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            gen.writeString(sdf.format(value));
        }
    }

    class Date2DateTimeStringSerializer extends JsonSerializer<Date> {

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            gen.writeString(sdf.format(value));
        }
    }

    class Date2LongSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            gen.writeNumber(value.getTime() / 1000);
        }
    }

    class Long2StringSerializer extends JsonSerializer<Long> {
        @Override
        public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException, JsonProcessingException {
            gen.writeString(String.valueOf(value));
        }
    }
}
