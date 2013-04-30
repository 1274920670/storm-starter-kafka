package storm.kafkastarter;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import storm.bolt.PrinterBolt;
import storm.bolt.RandomlyAckBolt;
import storm.kafka.KafkaConfig.StaticHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.StringScheme;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;

public class KafkaTopology {

	public static void main(String[] args) {
		
		List<String> hostStrings = new ArrayList<String>();
		hostStrings.add("localhost");
		SpoutConfig kafkaConfig = new SpoutConfig(
				StaticHosts.fromHostString(hostStrings, 1), 
				"uten_ack",
				"/kafkastorm", 
				"uten_ack");
		kafkaConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		kafkaConfig.zkServers = ImmutableList.of("localhost");
		kafkaConfig.zkPort = 2181;
		
		

//		kafkaConfig.forceStartOffsetTime(2379);
		
		
		KafkaSpout spout = new KafkaSpout(kafkaConfig);
		
		TopologyBuilder bldr = new TopologyBuilder();
		bldr.setSpout("kafka-spout", spout);
//		bldr.setBolt("random-drop", new RandomlyAckBolt())
//			.shuffleGrouping("kafka-spout");
		bldr.setBolt("printer", new PrinterBolt())
			.shuffleGrouping("kafka-spout");
		
		
		Config conf = new Config();
		conf.setDebug(true);
		conf.setNumWorkers(2);

		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("test", conf, bldr.createTopology());
		
		
		
	}

}
