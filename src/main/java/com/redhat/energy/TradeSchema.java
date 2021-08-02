package com.redhat.energy;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;
import org.infinispan.protostream.types.java.math.BigDecimalAdapter;

import java.time.Instant;

@AutoProtoSchemaBuilder(includeClasses = { Trade.class, BigDecimalAdapter.class }, schemaPackageName = "trade")
public interface TradeSchema extends GeneratedSchema {
}
