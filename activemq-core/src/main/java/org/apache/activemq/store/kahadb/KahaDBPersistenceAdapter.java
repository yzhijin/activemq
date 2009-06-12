/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.store.kahadb;

import org.apache.activeio.journal.Journal;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.store.MessageStore;
import org.apache.activemq.store.PersistenceAdapter;
import org.apache.activemq.store.TopicMessageStore;
import org.apache.activemq.store.TransactionStore;
import org.apache.activemq.usage.SystemUsage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
/**
 * An implementation of {@link PersistenceAdapter} designed for use with a
 * {@link Journal} and then check pointing asynchronously on a timeout with some
 * other long term persistent storage.
 * 
 * @org.apache.xbean.XBean element="KahaDB"
 * @version $Revision: 1.17 $
 */
public class KahaDBPersistenceAdapter implements PersistenceAdapter {
    private KahaDBStore letter = new KahaDBStore();
    

    /**
     * @param context
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#beginTransaction(org.apache.activemq.broker.ConnectionContext)
     */
    public void beginTransaction(ConnectionContext context) throws IOException {
        this.letter.beginTransaction(context);
    }

    /**
     * @param sync
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#checkpoint(boolean)
     */
    public void checkpoint(boolean sync) throws IOException {
        this.letter.checkpoint(sync);
    }

    /**
     * @param context
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#commitTransaction(org.apache.activemq.broker.ConnectionContext)
     */
    public void commitTransaction(ConnectionContext context) throws IOException {
        this.letter.commitTransaction(context);
    }

    /**
     * @param destination
     * @return MessageStore
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#createQueueMessageStore(org.apache.activemq.command.ActiveMQQueue)
     */
    public MessageStore createQueueMessageStore(ActiveMQQueue destination) throws IOException {
        return this.letter.createQueueMessageStore(destination);
    }

    /**
     * @param destination
     * @return TopicMessageStore
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#createTopicMessageStore(org.apache.activemq.command.ActiveMQTopic)
     */
    public TopicMessageStore createTopicMessageStore(ActiveMQTopic destination) throws IOException {
        return this.letter.createTopicMessageStore(destination);
    }

    /**
     * @return TrandactionStore
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#createTransactionStore()
     */
    public TransactionStore createTransactionStore() throws IOException {
        return this.letter.createTransactionStore();
    }

    /**
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#deleteAllMessages()
     */
    public void deleteAllMessages() throws IOException {
        this.letter.deleteAllMessages();
    }

    /**
     * @return destinations
     * @see org.apache.activemq.store.PersistenceAdapter#getDestinations()
     */
    public Set<ActiveMQDestination> getDestinations() {
        return this.letter.getDestinations();
    }

    /**
     * @return lastMessageBrokerSequenceId
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#getLastMessageBrokerSequenceId()
     */
    public long getLastMessageBrokerSequenceId() throws IOException {
        return this.letter.getLastMessageBrokerSequenceId();
    }

    /**
     * @param destination
     * @see org.apache.activemq.store.PersistenceAdapter#removeQueueMessageStore(org.apache.activemq.command.ActiveMQQueue)
     */
    public void removeQueueMessageStore(ActiveMQQueue destination) {
        this.letter.removeQueueMessageStore(destination);
    }

    /**
     * @param destination
     * @see org.apache.activemq.store.PersistenceAdapter#removeTopicMessageStore(org.apache.activemq.command.ActiveMQTopic)
     */
    public void removeTopicMessageStore(ActiveMQTopic destination) {
        this.letter.removeTopicMessageStore(destination);
    }

    /**
     * @param context
     * @throws IOException
     * @see org.apache.activemq.store.PersistenceAdapter#rollbackTransaction(org.apache.activemq.broker.ConnectionContext)
     */
    public void rollbackTransaction(ConnectionContext context) throws IOException {
        this.letter.rollbackTransaction(context);
    }

    /**
     * @param brokerName
     * @see org.apache.activemq.store.PersistenceAdapter#setBrokerName(java.lang.String)
     */
    public void setBrokerName(String brokerName) {
        this.letter.setBrokerName(brokerName);
    }

    

    /**
     * @param usageManager
     * @see org.apache.activemq.store.PersistenceAdapter#setUsageManager(org.apache.activemq.usage.SystemUsage)
     */
    public void setUsageManager(SystemUsage usageManager) {
        this.letter.setUsageManager(usageManager);
    }

    /**
     * @return the size of the store
     * @see org.apache.activemq.store.PersistenceAdapter#size()
     */
    public long size() {
        return this.letter.size();
    }

    /**
     * @throws Exception
     * @see org.apache.activemq.Service#start()
     */
    public void start() throws Exception {
        this.letter.start();
    }

    /**
     * @throws Exception
     * @see org.apache.activemq.Service#stop()
     */
    public void stop() throws Exception {
        this.letter.stop();
    }

    /**
     * Get the journalMaxFileLength
     * @return the journalMaxFileLength
     */
    public int getJournalMaxFileLength() {
        return this.letter.getJournalMaxFileLength();
    }

    /**
     * @param journalMaxFileLength 
     * When set using XBean, you can use values such as: "20
     * mb", "1024 kb", or "1 gb"
     * 
     * @org.apache.xbean.Property propertyEditor="org.apache.activemq.util.MemoryPropertyEditor"
     */
    public void setJournalMaxFileLength(int journalMaxFileLength) {
        this.letter.setJournalMaxFileLength(journalMaxFileLength);
    }

    /**
     * Get the checkpointInterval
     * @return the checkpointInterval
     */
    public long getCheckpointInterval() {
        return this.letter.getCheckpointInterval();
    }

    /**
     * Set the checkpointInterval
     * @param checkpointInterval the checkpointInterval to set
     */
    public void setCheckpointInterval(long checkpointInterval) {
        this.letter.setCheckpointInterval(checkpointInterval);
    }

    /**
     * Get the cleanupInterval
     * @return the cleanupInterval
     */
    public long getCleanupInterval() {
        return this.letter.getCleanupInterval();
    }

    /**
     * Set the cleanupInterval
     * @param cleanupInterval the cleanupInterval to set
     */
    public void setCleanupInterval(long cleanupInterval) {
        this.letter.setCleanupInterval(cleanupInterval);
    }

    /**
     * Get the indexWriteBatchSize
     * @return the indexWriteBatchSize
     */
    public int getIndexWriteBatchSize() {
        return this.letter.getIndexWriteBatchSize();
    }

    /**
     * Set the indexWriteBatchSize
     * @param indexWriteBatchSize the indexWriteBatchSize to set
     */
    public void setIndexWriteBatchSize(int indexWriteBatchSize) {
        this.letter.setIndexWriteBatchSize(indexWriteBatchSize);
    }

    /**
     * Get the enableIndexWriteAsync
     * @return the enableIndexWriteAsync
     */
    public boolean isEnableIndexWriteAsync() {
        return this.letter.isEnableIndexWriteAsync();
    }

    /**
     * Set the enableIndexWriteAsync
     * @param enableIndexWriteAsync the enableIndexWriteAsync to set
     */
    public void setEnableIndexWriteAsync(boolean enableIndexWriteAsync) {
        this.letter.setEnableIndexWriteAsync(enableIndexWriteAsync);
    }

    /**
     * Get the directory
     * @return the directory
     */
    public File getDirectory() {
        return this.letter.getDirectory();
    }
    
    /**
     * @param dir
     * @see org.apache.activemq.store.PersistenceAdapter#setDirectory(java.io.File)
     */
    public void setDirectory(File dir) {
        this.letter.setDirectory(dir);
    }

    /**
     * Get the enableJournalDiskSyncs
     * @return the enableJournalDiskSyncs
     */
    public boolean isEnableJournalDiskSyncs() {
        return this.letter.isEnableJournalDiskSyncs();
    }

    /**
     * Set the enableJournalDiskSyncs
     * @param enableJournalDiskSyncs the enableJournalDiskSyncs to set
     */
    public void setEnableJournalDiskSyncs(boolean enableJournalDiskSyncs) {
        this.letter.setEnableJournalDiskSyncs(enableJournalDiskSyncs);
    }
}
