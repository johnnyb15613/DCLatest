/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.apps.dashclock.phone;

import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Private API. Will likely break in the future.
 *
 * Excerpt from frameworks/base/core/java/android/provider/Telephony.java.
 */
public final class TelephonyProviderConstants {
    private TelephonyProviderConstants() {
    }

    /**
     * Base columns for tables that contain text based SMSs.
     */
    public interface TextBasedSmsColumns {
        /**
         * The type of the message
         * <P>Type: INTEGER</P>
         */
        public static final String TYPE = "type";

        public static final int MESSAGE_TYPE_ALL    = 0;
        public static final int MESSAGE_TYPE_INBOX  = 1;
        public static final int MESSAGE_TYPE_SENT   = 2;
        public static final int MESSAGE_TYPE_DRAFT  = 3;
        public static final int MESSAGE_TYPE_OUTBOX = 4;
        public static final int MESSAGE_TYPE_FAILED = 5; // for failed outgoing messages
        public static final int MESSAGE_TYPE_QUEUED = 6; // for messages to send later


        /**
         * The thread ID of the message
         * <P>Type: INTEGER</P>
         */
        public static final String THREAD_ID = "thread_id";

        /**
         * The address of the other party
         * <P>Type: TEXT</P>
         */
        public static final String ADDRESS = "address";

        /**
         * The person ID of the sender
         * <P>Type: INTEGER (long)</P>
         */
        public static final String PERSON_ID = "person";

        /**
         * The date the message was received
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DATE = "date";

        /**
         * The date the message was sent
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DATE_SENT = "date_sent";

        /**
         * Has the message been read
         * <P>Type: INTEGER (boolean)</P>
         */
        public static final String READ = "read";

        /**
         * Indicates whether this message has been seen by the user. The "seen" flag will be
         * used to figure out whether we need to throw up a statusbar notification or not.
         */
        public static final String SEEN = "seen";

        /**
         * The TP-Status value for the message, or -1 if no status has
         * been received
         */
        public static final String STATUS = "status";

        public static final int STATUS_NONE = -1;
        public static final int STATUS_COMPLETE = 0;
        public static final int STATUS_PENDING = 32;
        public static final int STATUS_FAILED = 64;

        /**
         * The subject of the message, if present
         * <P>Type: TEXT</P>
         */
        public static final String SUBJECT = "subject";

        /**
         * The body of the message
         * <P>Type: TEXT</P>
         */
        public static final String BODY = "body";

        /**
         * The id of the sender of the conversation, if present
         * <P>Type: INTEGER (reference to item in content://contacts/people)</P>
         */
        public static final String PERSON = "person";

        /**
         * The protocol identifier code
         * <P>Type: INTEGER</P>
         */
        public static final String PROTOCOL = "protocol";

        /**
         * Whether the <code>TP-Reply-Path</code> bit was set on this message
         * <P>Type: BOOLEAN</P>
         */
        public static final String REPLY_PATH_PRESENT = "reply_path_present";

        /**
         * The service center (SC) through which to send the message, if present
         * <P>Type: TEXT</P>
         */
        public static final String SERVICE_CENTER = "service_center";

        /**
         * Has the message been locked?
         * <P>Type: INTEGER (boolean)</P>
         */
        public static final String LOCKED = "locked";

        /**
         * Error code associated with sending or receiving this message
         * <P>Type: INTEGER</P>
         */
        public static final String ERROR_CODE = "error_code";

        /**
         * Meta data used externally.
         * <P>Type: TEXT</P>
         */
        public static final String META_DATA = "meta_data";
}

    /**
     * Contains all text based SMS messages.
     */
    public static final class Sms implements BaseColumns, TextBasedSmsColumns {
        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =
            Uri.parse("content://sms");

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "date DESC";

        /**
         * Returns true iff the folder (message type) identifies an
         * outgoing message.
         */
        public static boolean isOutgoingFolder(int messageType) {
            return  (messageType == MESSAGE_TYPE_FAILED)
                    || (messageType == MESSAGE_TYPE_OUTBOX)
                    || (messageType == MESSAGE_TYPE_SENT)
                    || (messageType == MESSAGE_TYPE_QUEUED);
        }

        /**
         * Contains all text based SMS messages in the SMS app's inbox.
         */
        public static final class Inbox implements BaseColumns, TextBasedSmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri CONTENT_URI =
                Uri.parse("content://sms/inbox");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";
        }

        /**
         * Contains all sent text based SMS messages in the SMS app's.
         */
        public static final class Sent implements BaseColumns, TextBasedSmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri CONTENT_URI =
                    Uri.parse("content://sms/sent");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";
        }

        /**
         * Contains all sent text based SMS messages in the SMS app's.
         */
        public static final class Draft implements BaseColumns, TextBasedSmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri CONTENT_URI =
                    Uri.parse("content://sms/draft");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";
        }

        /**
         * Contains all pending outgoing text based SMS messages.
         */
        public static final class Outbox implements BaseColumns, TextBasedSmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri CONTENT_URI =
                Uri.parse("content://sms/outbox");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";
        }

        /**
         * Contains all sent text-based SMS messages in the SMS app's.
         */
        public static final class Conversations
                implements BaseColumns, TextBasedSmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri CONTENT_URI =
                Uri.parse("content://sms/conversations");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";

            /**
             * The first 45 characters of the body of the message
             * <P>Type: TEXT</P>
             */
            public static final String SNIPPET = "snippet";

            /**
             * The number of messages in the conversation
             * <P>Type: INTEGER</P>
             */
            public static final String MESSAGE_COUNT = "msg_count";
        }

        /**
         * Contains info about SMS related Intents that are broadcast.
         */
        public static final class Intents {
            /**
             * Set by BroadcastReceiver. Indicates the message was handled
             * successfully.
             */
            public static final int RESULT_SMS_HANDLED = 1;

            /**
             * Set by BroadcastReceiver. Indicates a generic error while
             * processing the message.
             */
            public static final int RESULT_SMS_GENERIC_ERROR = 2;

            /**
             * Set by BroadcastReceiver. Indicates insufficient memory to store
             * the message.
             */
            public static final int RESULT_SMS_OUT_OF_MEMORY = 3;

            /**
             * Set by BroadcastReceiver. Indicates the message, while
             * possibly valid, is of a format or encoding that is not
             * supported.
             */
            public static final int RESULT_SMS_UNSUPPORTED = 4;

            /**
             * Broadcast Action: A new text based SMS message has been received
             * by the device. The intent will have the following extra
             * values:</p>
             *
             * <ul>
             *   <li><em>pdus</em> - An Object[] od byte[]s containing the PDUs
             *   that make up the message.</li>
             * </ul>
             *
             * <p>The extra values can be extracted using
             * {@link #getMessagesFromIntent(android.content.Intent)}.</p>
             *
             * <p>If a BroadcastReceiver encounters an error while processing
             * this intent it should set the result code appropriately.</p>
             */
            public static final String SMS_RECEIVED_ACTION =
                    "android.provider.Telephony.SMS_RECEIVED";

            /**
             * Broadcast Action: A new data based SMS message has been received
             * by the device. The intent will have the following extra
             * values:</p>
             *
             * <ul>
             *   <li><em>pdus</em> - An Object[] of byte[]s containing the PDUs
             *   that make up the message.</li>
             * </ul>
             *
             * <p>The extra values can be extracted using
             * {@link #getMessagesFromIntent(android.content.Intent)}.</p>
             *
             * <p>If a BroadcastReceiver encounters an error while processing
             * this intent it should set the result code appropriately.</p>
             */
            public static final String DATA_SMS_RECEIVED_ACTION =
                    "android.intent.action.DATA_SMS_RECEIVED";

            /**
             * Broadcast Action: A new WAP PUSH message has been received by the
             * device. The intent will have the following extra
             * values:</p>
             *
             * <ul>
             *   <li><em>transactionId (Integer)</em> - The WAP transaction ID</li>
             *   <li><em>pduType (Integer)</em> - The WAP PDU type</li>
             *   <li><em>header (byte[])</em> - The header of the message</li>
             *   <li><em>data (byte[])</em> - The data payload of the message</li>
             *   <li><em>contentTypeParameters (HashMap&lt;String,String&gt;)</em>
             *   - Any parameters associated with the content type
             *   (decoded from the WSP Content-Type header)</li>
             * </ul>
             *
             * <p>If a BroadcastReceiver encounters an error while processing
             * this intent it should set the result code appropriately.</p>
             *
             * <p>The contentTypeParameters extra value is map of content parameters keyed by
             * their names.</p>
             *
             * <p>If any unassigned well-known parameters are encountered, the key of the map will
             * be 'unassigned/0x...', where '...' is the hex value of the unassigned parameter.  If
             * a parameter has No-Value the value in the map will be null.</p>
             */
            public static final String WAP_PUSH_RECEIVED_ACTION =
                    "android.provider.Telephony.WAP_PUSH_RECEIVED";

            /**
             * Broadcast Action: A new Cell Broadcast message has been received
             * by the device. The intent will have the following extra
             * values:</p>
             *
             * <ul>
             *   <li><em>message</em> - An SmsCbMessage object containing the broadcast message
             *   data. This is not an emergency alert, so ETWS and CMAS data will be null.</li>
             * </ul>
             *
             * <p>The extra values can be extracted using
             * {@link #getMessagesFromIntent(android.content.Intent)}.</p>
             *
             * <p>If a BroadcastReceiver encounters an error while processing
             * this intent it should set the result code appropriately.</p>
             */
            public static final String SMS_CB_RECEIVED_ACTION =
                    "android.provider.Telephony.SMS_CB_RECEIVED";

            /**
             * Broadcast Action: A new Emergency Broadcast message has been received
             * by the device. The intent will have the following extra
             * values:</p>
             *
             * <ul>
             *   <li><em>message</em> - An SmsCbMessage object containing the broadcast message
             *   data, including ETWS or CMAS warning notification info if present.</li>
             * </ul>
             *
             * <p>The extra values can be extracted using
             * {@link #getMessagesFromIntent(android.content.Intent)}.</p>
             *
             * <p>If a BroadcastReceiver encounters an error while processing
             * this intent it should set the result code appropriately.</p>
             */
            public static final String SMS_EMERGENCY_CB_RECEIVED_ACTION =
                    "android.provider.Telephony.SMS_EMERGENCY_CB_RECEIVED";

            /**
             * Broadcast Action: A new CDMA SMS has been received containing Service Category
             * Program Data (updates the list of enabled broadcast channels). The intent will
             * have the following extra values:</p>
             *
             * <ul>
             *   <li><em>operations</em> - An array of CdmaSmsCbProgramData objects containing
             *   the service category operations (add/delete/clear) to perform.</li>
             * </ul>
             *
             * <p>The extra values can be extracted using
             * {@link #getMessagesFromIntent(android.content.Intent)}.</p>
             *
             * <p>If a BroadcastReceiver encounters an error while processing
             * this intent it should set the result code appropriately.</p>
             */
            public static final String SMS_SERVICE_CATEGORY_PROGRAM_DATA_RECEIVED_ACTION =
                    "android.provider.Telephony.SMS_SERVICE_CATEGORY_PROGRAM_DATA_RECEIVED";

            /**
             * Broadcast Action: The SIM storage for SMS messages is full.  If
             * space is not freed, messages targeted for the SIM (class 2) may
             * not be saved.
             */
            public static final String SIM_FULL_ACTION =
                    "android.provider.Telephony.SIM_FULL";

            /**
             * Broadcast Action: An incoming SMS has been rejected by the
             * telephony framework.  This intent is sent in lieu of any
             * of the RECEIVED_ACTION intents.  The intent will have the
             * following extra value:</p>
             *
             * <ul>
             *   <li><em>result</em> - An int result code, eg,
             *   <code>{@link #RESULT_SMS_OUT_OF_MEMORY}</code>,
             *   indicating the error returned to the network.</li>
             * </ul>

             */
            public static final String SMS_REJECTED_ACTION =
                "android.provider.Telephony.SMS_REJECTED";
        }
    }

    /**
     * Base columns for tables that contain MMSs.
     */
    public interface BaseMmsColumns extends BaseColumns {

        public static final int MESSAGE_BOX_ALL    = 0;
        public static final int MESSAGE_BOX_INBOX  = 1;
        public static final int MESSAGE_BOX_SENT   = 2;
        public static final int MESSAGE_BOX_DRAFTS = 3;
        public static final int MESSAGE_BOX_OUTBOX = 4;

        /**
         * The date the message was received.
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DATE = "date";

        /**
         * The date the message was sent.
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DATE_SENT = "date_sent";

        /**
         * The box which the message belong to, for example, MESSAGE_BOX_INBOX.
         * <P>Type: INTEGER</P>
         */
        public static final String MESSAGE_BOX = "msg_box";

        /**
         * Has the message been read.
         * <P>Type: INTEGER (boolean)</P>
         */
        public static final String READ = "read";

        /**
         * Indicates whether this message has been seen by the user. The "seen" flag will be
         * used to figure out whether we need to throw up a statusbar notification or not.
         */
        public static final String SEEN = "seen";

        /**
         * The Message-ID of the message.
         * <P>Type: TEXT</P>
         */
        public static final String MESSAGE_ID = "m_id";

        /**
         * The subject of the message, if present.
         * <P>Type: TEXT</P>
         */
        public static final String SUBJECT = "sub";

        /**
         * The character set of the subject, if present.
         * <P>Type: INTEGER</P>
         */
        public static final String SUBJECT_CHARSET = "sub_cs";

        /**
         * The Content-Type of the message.
         * <P>Type: TEXT</P>
         */
        public static final String CONTENT_TYPE = "ct_t";

        /**
         * The Content-Location of the message.
         * <P>Type: TEXT</P>
         */
        public static final String CONTENT_LOCATION = "ct_l";

        /**
         * The address of the sender.
         * <P>Type: TEXT</P>
         */
        public static final String FROM = "from";

        /**
         * The address of the recipients.
         * <P>Type: TEXT</P>
         */
        public static final String TO = "to";

        /**
         * The address of the cc. recipients.
         * <P>Type: TEXT</P>
         */
        public static final String CC = "cc";

        /**
         * The address of the bcc. recipients.
         * <P>Type: TEXT</P>
         */
        public static final String BCC = "bcc";

        /**
         * The expiry time of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String EXPIRY = "exp";

        /**
         * The class of the message.
         * <P>Type: TEXT</P>
         */
        public static final String MESSAGE_CLASS = "m_cls";

        /**
         * The type of the message defined by MMS spec.
         * <P>Type: INTEGER</P>
         */
        public static final String MESSAGE_TYPE = "m_type";

        /**
         * The version of specification that this message conform.
         * <P>Type: INTEGER</P>
         */
        public static final String MMS_VERSION = "v";

        /**
         * The size of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String MESSAGE_SIZE = "m_size";

        /**
         * The priority of the message.
         * <P>Type: TEXT</P>
         */
        public static final String PRIORITY = "pri";

        /**
         * The read-report of the message.
         * <P>Type: TEXT</P>
         */
        public static final String READ_REPORT = "rr";

        /**
         * Whether the report is allowed.
         * <P>Type: TEXT</P>
         */
        public static final String REPORT_ALLOWED = "rpt_a";

        /**
         * The response-status of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String RESPONSE_STATUS = "resp_st";

        /**
         * The status of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String STATUS = "st";

        /**
         * The transaction-id of the message.
         * <P>Type: TEXT</P>
         */
        public static final String TRANSACTION_ID = "tr_id";

        /**
         * The retrieve-status of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String RETRIEVE_STATUS = "retr_st";

        /**
         * The retrieve-text of the message.
         * <P>Type: TEXT</P>
         */
        public static final String RETRIEVE_TEXT = "retr_txt";

        /**
         * The character set of the retrieve-text.
         * <P>Type: TEXT</P>
         */
        public static final String RETRIEVE_TEXT_CHARSET = "retr_txt_cs";

        /**
         * The read-status of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String READ_STATUS = "read_status";

        /**
         * The content-class of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String CONTENT_CLASS = "ct_cls";

        /**
         * The delivery-report of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String DELIVERY_REPORT = "d_rpt";

        /**
         * The delivery-time-token of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String DELIVERY_TIME_TOKEN = "d_tm_tok";

        /**
         * The delivery-time of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String DELIVERY_TIME = "d_tm";

        /**
         * The response-text of the message.
         * <P>Type: TEXT</P>
         */
        public static final String RESPONSE_TEXT = "resp_txt";

        /**
         * The sender-visibility of the message.
         * <P>Type: TEXT</P>
         */
        public static final String SENDER_VISIBILITY = "s_vis";

        /**
         * The reply-charging of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String REPLY_CHARGING = "r_chg";

        /**
         * The reply-charging-deadline-token of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String REPLY_CHARGING_DEADLINE_TOKEN = "r_chg_dl_tok";

        /**
         * The reply-charging-deadline of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String REPLY_CHARGING_DEADLINE = "r_chg_dl";

        /**
         * The reply-charging-id of the message.
         * <P>Type: TEXT</P>
         */
        public static final String REPLY_CHARGING_ID = "r_chg_id";

        /**
         * The reply-charging-size of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String REPLY_CHARGING_SIZE = "r_chg_sz";

        /**
         * The previously-sent-by of the message.
         * <P>Type: TEXT</P>
         */
        public static final String PREVIOUSLY_SENT_BY = "p_s_by";

        /**
         * The previously-sent-date of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String PREVIOUSLY_SENT_DATE = "p_s_d";

        /**
         * The store of the message.
         * <P>Type: TEXT</P>
         */
        public static final String STORE = "store";

        /**
         * The mm-state of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String MM_STATE = "mm_st";

        /**
         * The mm-flags-token of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String MM_FLAGS_TOKEN = "mm_flg_tok";

        /**
         * The mm-flags of the message.
         * <P>Type: TEXT</P>
         */
        public static final String MM_FLAGS = "mm_flg";

        /**
         * The store-status of the message.
         * <P>Type: TEXT</P>
         */
        public static final String STORE_STATUS = "store_st";

        /**
         * The store-status-text of the message.
         * <P>Type: TEXT</P>
         */
        public static final String STORE_STATUS_TEXT = "store_st_txt";

        /**
         * The stored of the message.
         * <P>Type: TEXT</P>
         */
        public static final String STORED = "stored";

        /**
         * The totals of the message.
         * <P>Type: TEXT</P>
         */
        public static final String TOTALS = "totals";

        /**
         * The mbox-totals of the message.
         * <P>Type: TEXT</P>
         */
        public static final String MBOX_TOTALS = "mb_t";

        /**
         * The mbox-totals-token of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String MBOX_TOTALS_TOKEN = "mb_t_tok";

        /**
         * The quotas of the message.
         * <P>Type: TEXT</P>
         */
        public static final String QUOTAS = "qt";

        /**
         * The mbox-quotas of the message.
         * <P>Type: TEXT</P>
         */
        public static final String MBOX_QUOTAS = "mb_qt";

        /**
         * The mbox-quotas-token of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String MBOX_QUOTAS_TOKEN = "mb_qt_tok";

        /**
         * The message-count of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String MESSAGE_COUNT = "m_cnt";

        /**
         * The start of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String START = "start";

        /**
         * The distribution-indicator of the message.
         * <P>Type: TEXT</P>
         */
        public static final String DISTRIBUTION_INDICATOR = "d_ind";

        /**
         * The element-descriptor of the message.
         * <P>Type: TEXT</P>
         */
        public static final String ELEMENT_DESCRIPTOR = "e_des";

        /**
         * The limit of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String LIMIT = "limit";

        /**
         * The recommended-retrieval-mode of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String RECOMMENDED_RETRIEVAL_MODE = "r_r_mod";

        /**
         * The recommended-retrieval-mode-text of the message.
         * <P>Type: TEXT</P>
         */
        public static final String RECOMMENDED_RETRIEVAL_MODE_TEXT = "r_r_mod_txt";

        /**
         * The status-text of the message.
         * <P>Type: TEXT</P>
         */
        public static final String STATUS_TEXT = "st_txt";

        /**
         * The applic-id of the message.
         * <P>Type: TEXT</P>
         */
        public static final String APPLIC_ID = "apl_id";

        /**
         * The reply-applic-id of the message.
         * <P>Type: TEXT</P>
         */
        public static final String REPLY_APPLIC_ID = "r_apl_id";

        /**
         * The aux-applic-id of the message.
         * <P>Type: TEXT</P>
         */
        public static final String AUX_APPLIC_ID = "aux_apl_id";

        /**
         * The drm-content of the message.
         * <P>Type: TEXT</P>
         */
        public static final String DRM_CONTENT = "drm_c";

        /**
         * The adaptation-allowed of the message.
         * <P>Type: TEXT</P>
         */
        public static final String ADAPTATION_ALLOWED = "adp_a";

        /**
         * The replace-id of the message.
         * <P>Type: TEXT</P>
         */
        public static final String REPLACE_ID = "repl_id";

        /**
         * The cancel-id of the message.
         * <P>Type: TEXT</P>
         */
        public static final String CANCEL_ID = "cl_id";

        /**
         * The cancel-status of the message.
         * <P>Type: INTEGER</P>
         */
        public static final String CANCEL_STATUS = "cl_st";

        /**
         * The thread ID of the message
         * <P>Type: INTEGER</P>
         */
        public static final String THREAD_ID = "thread_id";

        /**
         * Has the message been locked?
         * <P>Type: INTEGER (boolean)</P>
         */
        public static final String LOCKED = "locked";

        /**
         * Meta data used externally.
         * <P>Type: TEXT</P>
         */
        public static final String META_DATA = "meta_data";
    }

    /**
     * Columns for the "canonical_addresses" table used by MMS and
     * SMS."
     */
    public interface CanonicalAddressesColumns extends BaseColumns {
        /**
         * An address used in MMS or SMS.  Email addresses are
         * converted to lower case and are compared by string
         * equality.  Other addresses are compared using
         * PHONE_NUMBERS_EQUAL.
         * <P>Type: TEXT</P>
         */
        public static final String ADDRESS = "address";
    }

    /**
     * Columns for the "threads" table used by MMS and SMS.
     */
    public interface ThreadsColumns extends BaseColumns {
        /**
         * The date at which the thread was created.
         *
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DATE = "date";

        /**
         * A string encoding of the recipient IDs of the recipients of
         * the message, in numerical order and separated by spaces.
         * <P>Type: TEXT</P>
         */
        public static final String RECIPIENT_IDS = "recipient_ids";

        /**
         * The message count of the thread.
         * <P>Type: INTEGER</P>
         */
        public static final String MESSAGE_COUNT = "message_count";
        /**
         * Indicates whether all messages of the thread have been read.
         * <P>Type: INTEGER</P>
         */
        public static final String READ = "read";

        /**
         * The snippet of the latest message in the thread.
         * <P>Type: TEXT</P>
         */
        public static final String SNIPPET = "snippet";
        /**
         * The charset of the snippet.
         * <P>Type: INTEGER</P>
         */
        public static final String SNIPPET_CHARSET = "snippet_cs";
        /**
         * Type of the thread, either Threads.COMMON_THREAD or
         * Threads.BROADCAST_THREAD.
         * <P>Type: INTEGER</P>
         */
        public static final String TYPE = "type";
        /**
         * Indicates whether there is a transmission error in the thread.
         * <P>Type: INTEGER</P>
         */
        public static final String ERROR = "error";
        /**
         * Indicates whether this thread contains any attachments.
         * <P>Type: INTEGER</P>
         */
        public static final String HAS_ATTACHMENT = "has_attachment";
    }

    /**
     * Helper functions for the "threads" table used by MMS and SMS.
     */
    public static final class Threads implements ThreadsColumns {
        private static final String[] ID_PROJECTION = { BaseColumns._ID };
        private static final String STANDARD_ENCODING = "UTF-8";
        private static final Uri THREAD_ID_CONTENT_URI = Uri.parse(
                "content://mms-sms/threadID");
        public static final Uri CONTENT_URI = Uri.withAppendedPath(
                MmsSms.CONTENT_URI, "conversations");
        public static final Uri OBSOLETE_THREADS_URI = Uri.withAppendedPath(
                CONTENT_URI, "obsolete");

        public static final int COMMON_THREAD    = 0;
        public static final int BROADCAST_THREAD = 1;

        // No one should construct an instance of this class.
        private Threads() {
        }
    }

    /**
     * Contains all MMS messages.
     */
    public static final class Mms implements BaseMmsColumns {
        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI = Uri.parse("content://mms");

        public static final Uri REPORT_REQUEST_URI = Uri.withAppendedPath(
                                            CONTENT_URI, "report-request");

        public static final Uri REPORT_STATUS_URI = Uri.withAppendedPath(
                                            CONTENT_URI, "report-status");

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "date DESC";

        /**
         * mailbox         =       name-addr
         * name-addr       =       [display-name] angle-addr
         * angle-addr      =       [CFWS] "<" addr-spec ">" [CFWS]
         */
        public static final Pattern NAME_ADDR_EMAIL_PATTERN =
                Pattern.compile("\\s*(\"[^\"]*\"|[^<>\"]+)\\s*<([^<>]+)>\\s*");

        /**
         * quoted-string   =       [CFWS]
         *                         DQUOTE *([FWS] qcontent) [FWS] DQUOTE
         *                         [CFWS]
         */
        public static final Pattern QUOTED_STRING_PATTERN =
                Pattern.compile("\\s*\"([^\"]*)\"\\s*");

        public static final String getMessageBoxName(int msgBox) {
            switch (msgBox) {
                case MESSAGE_BOX_ALL:
                    return "all";
                case MESSAGE_BOX_INBOX:
                    return "inbox";
                case MESSAGE_BOX_SENT:
                    return "sent";
                case MESSAGE_BOX_DRAFTS:
                    return "drafts";
                case MESSAGE_BOX_OUTBOX:
                    return "outbox";
                default:
                    throw new IllegalArgumentException("Invalid message box: " + msgBox);
            }
        }

        public static String extractAddrSpec(String address) {
            Matcher match = NAME_ADDR_EMAIL_PATTERN.matcher(address);

            if (match.matches()) {
                return match.group(2);
            }
            return address;
        }

        /**
         * Returns true if the address is an email address
         *
         * @param address the input address to be tested
         * @return true if address is an email address
         */
        public static boolean isEmailAddress(String address) {
            if (TextUtils.isEmpty(address)) {
                return false;
            }

            String s = extractAddrSpec(address);
            Matcher match = Patterns.EMAIL_ADDRESS.matcher(s);
            return match.matches();
        }

        /**
         * Returns true if the number is a Phone number
         *
         * @param number the input number to be tested
         * @return true if number is a Phone number
         */
        public static boolean isPhoneNumber(String number) {
            if (TextUtils.isEmpty(number)) {
                return false;
            }

            Matcher match = Patterns.PHONE.matcher(number);
            return match.matches();
        }

        /**
         * Contains all MMS messages in the MMS app's inbox.
         */
        public static final class Inbox implements BaseMmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri
                    CONTENT_URI = Uri.parse("content://mms/inbox");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";
        }

        /**
         * Contains all MMS messages in the MMS app's sent box.
         */
        public static final class Sent implements BaseMmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri
                    CONTENT_URI = Uri.parse("content://mms/sent");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";
        }

        /**
         * Contains all MMS messages in the MMS app's drafts box.
         */
        public static final class Draft implements BaseMmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri
                    CONTENT_URI = Uri.parse("content://mms/drafts");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";
        }

        /**
         * Contains all MMS messages in the MMS app's outbox.
         */
        public static final class Outbox implements BaseMmsColumns {
            /**
             * The content:// style URL for this table
             */
            public static final Uri
                    CONTENT_URI = Uri.parse("content://mms/outbox");

            /**
             * The default sort order for this table
             */
            public static final String DEFAULT_SORT_ORDER = "date DESC";
        }

        public static final class Addr implements BaseColumns {
            /**
             * The ID of MM which this address entry belongs to.
             */
            public static final String MSG_ID = "msg_id";

            /**
             * The ID of contact entry in Phone Book.
             */
            public static final String CONTACT_ID = "contact_id";

            /**
             * The address text.
             */
            public static final String ADDRESS = "address";

            /**
             * Type of address, must be one of PduHeaders.BCC,
             * PduHeaders.CC, PduHeaders.FROM, PduHeaders.TO.
             */
            public static final String TYPE = "type";

            /**
             * Character set of this entry.
             */
            public static final String CHARSET = "charset";
        }

        public static final class Part implements BaseColumns {
            /**
             * The identifier of the message which this part belongs to.
             * <P>Type: INTEGER</P>
             */
            public static final String MSG_ID = "mid";

            /**
             * The order of the part.
             * <P>Type: INTEGER</P>
             */
            public static final String SEQ = "seq";

            /**
             * The content type of the part.
             * <P>Type: TEXT</P>
             */
            public static final String CONTENT_TYPE = "ct";

            /**
             * The name of the part.
             * <P>Type: TEXT</P>
             */
            public static final String NAME = "name";

            /**
             * The charset of the part.
             * <P>Type: TEXT</P>
             */
            public static final String CHARSET = "chset";

            /**
             * The file name of the part.
             * <P>Type: TEXT</P>
             */
            public static final String FILENAME = "fn";

            /**
             * The content disposition of the part.
             * <P>Type: TEXT</P>
             */
            public static final String CONTENT_DISPOSITION = "cd";

            /**
             * The content ID of the part.
             * <P>Type: INTEGER</P>
             */
            public static final String CONTENT_ID = "cid";

            /**
             * The content location of the part.
             * <P>Type: INTEGER</P>
             */
            public static final String CONTENT_LOCATION = "cl";

            /**
             * The start of content-type of the message.
             * <P>Type: INTEGER</P>
             */
            public static final String CT_START = "ctt_s";

            /**
             * The type of content-type of the message.
             * <P>Type: TEXT</P>
             */
            public static final String CT_TYPE = "ctt_t";

            /**
             * The location(on filesystem) of the binary data of the part.
             * <P>Type: INTEGER</P>
             */
            public static final String _DATA = "_data";

            public static final String TEXT = "text";

        }

        public static final class Rate {
            public static final Uri CONTENT_URI = Uri.withAppendedPath(
                    Mms.CONTENT_URI, "rate");
            /**
             * When a message was successfully sent.
             * <P>Type: INTEGER</P>
             */
            public static final String SENT_TIME = "sent_time";
        }

        public static final class Intents {
            private Intents() {
                // Non-instantiatable.
            }

            /**
             * The extra field to store the contents of the Intent,
             * which should be an array of Uri.
             */
            public static final String EXTRA_CONTENTS = "contents";
            /**
             * The extra field to store the type of the contents,
             * which should be an array of String.
             */
            public static final String EXTRA_TYPES    = "types";
            /**
             * The extra field to store the 'Cc' addresses.
             */
            public static final String EXTRA_CC       = "cc";
            /**
             * The extra field to store the 'Bcc' addresses;
             */
            public static final String EXTRA_BCC      = "bcc";
            /**
             * The extra field to store the 'Subject'.
             */
            public static final String EXTRA_SUBJECT  = "subject";
            /**
             * Indicates that the contents of specified URIs were changed.
             * The application which is showing or caching these contents
             * should be updated.
             */
            public static final String
            CONTENT_CHANGED_ACTION = "android.intent.action.CONTENT_CHANGED";
            /**
             * An extra field which stores the URI of deleted contents.
             */
            public static final String DELETED_CONTENTS = "deleted_contents";
        }
    }

    /**
     * Contains all MMS and SMS messages.
     */
    public static final class MmsSms implements BaseColumns {
        /**
         * The column to distinguish SMS &amp; MMS messages in query results.
         */
        public static final String TYPE_DISCRIMINATOR_COLUMN =
                "transport_type";

        public static final Uri CONTENT_URI = Uri.parse("content://mms-sms/");

        public static final Uri CONTENT_CONVERSATIONS_URI = Uri.parse(
                "content://mms-sms/conversations");

        public static final Uri CONTENT_FILTER_BYPHONE_URI = Uri.parse(
                "content://mms-sms/messages/byphone");

        public static final Uri CONTENT_UNDELIVERED_URI = Uri.parse(
                "content://mms-sms/undelivered");

        public static final Uri CONTENT_DRAFT_URI = Uri.parse(
                "content://mms-sms/draft");

        public static final Uri CONTENT_LOCKED_URI = Uri.parse(
                "content://mms-sms/locked");

        /***
         * Pass in a query parameter called "pattern" which is the text
         * to search for.
         * The sort order is fixed to be thread_id ASC,date DESC.
         */
        public static final Uri SEARCH_URI = Uri.parse(
                "content://mms-sms/search");

        // Constants for message protocol types.
        public static final int SMS_PROTO = 0;
        public static final int MMS_PROTO = 1;

        // Constants for error types of pending messages.
        public static final int NO_ERROR                      = 0;
        public static final int ERR_TYPE_GENERIC              = 1;
        public static final int ERR_TYPE_SMS_PROTO_TRANSIENT  = 2;
        public static final int ERR_TYPE_MMS_PROTO_TRANSIENT  = 3;
        public static final int ERR_TYPE_TRANSPORT_FAILURE    = 4;
        public static final int ERR_TYPE_GENERIC_PERMANENT    = 10;
        public static final int ERR_TYPE_SMS_PROTO_PERMANENT  = 11;
        public static final int ERR_TYPE_MMS_PROTO_PERMANENT  = 12;

        public static final class PendingMessages implements BaseColumns {
            public static final Uri CONTENT_URI = Uri.withAppendedPath(
                    MmsSms.CONTENT_URI, "pending");
            /**
             * The type of transport protocol(MMS or SMS).
             * <P>Type: INTEGER</P>
             */
            public static final String PROTO_TYPE = "proto_type";
            /**
             * The ID of the message to be sent or downloaded.
             * <P>Type: INTEGER</P>
             */
            public static final String MSG_ID = "msg_id";
            /**
             * The type of the message to be sent or downloaded.
             * This field is only valid for MM. For SM, its value is always
             * set to 0.
             */
            public static final String MSG_TYPE = "msg_type";
            /**
             * The type of the error code.
             * <P>Type: INTEGER</P>
             */
            public static final String ERROR_TYPE = "err_type";
            /**
             * The error code of sending/retrieving process.
             * <P>Type:  INTEGER</P>
             */
            public static final String ERROR_CODE = "err_code";
            /**
             * How many times we tried to send or download the message.
             * <P>Type:  INTEGER</P>
             */
            public static final String RETRY_INDEX = "retry_index";
            /**
             * The time to do next retry.
             */
            public static final String DUE_TIME = "due_time";
            /**
             * The time we last tried to send or download the message.
             */
            public static final String LAST_TRY = "last_try";
        }

        public static final class WordsTable {
            public static final String ID = "_id";
            public static final String SOURCE_ROW_ID = "source_id";
            public static final String TABLE_ID = "table_to_use";
            public static final String INDEXED_TEXT = "index_text";
        }
    }

    public static final class Carriers implements BaseColumns {
        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =
            Uri.parse("content://telephony/carriers");

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "name ASC";

        public static final String NAME = "name";

        public static final String APN = "apn";

        public static final String PROXY = "proxy";

        public static final String PORT = "port";

        public static final String MMSPROXY = "mmsproxy";

        public static final String MMSPORT = "mmsport";

        public static final String SERVER = "server";

        public static final String USER = "user";

        public static final String PASSWORD = "password";

        public static final String MMSC = "mmsc";

        public static final String MCC = "mcc";

        public static final String MNC = "mnc";

        public static final String NUMERIC = "numeric";

        public static final String AUTH_TYPE = "authtype";

        public static final String TYPE = "type";

        public static final String INACTIVE_TIMER = "inactivetimer";

        // Only if enabled try Data Connection.
        public static final String ENABLED = "enabled";

        // Rules apply based on class.
        public static final String CLASS = "class";

        /**
         * The protocol to be used to connect to this APN.
         *
         * One of the PDP_type values in TS 27.007 section 10.1.1.
         * For example, "IP", "IPV6", "IPV4V6", or "PPP".
         */
        public static final String PROTOCOL = "protocol";

        /**
          * The protocol to be used to connect to this APN when roaming.
          *
          * The syntax is the same as protocol.
          */
        public static final String ROAMING_PROTOCOL = "roaming_protocol";

        public static final String CURRENT = "current";

        /**
          * Current status of APN
          * true : enabled APN, false : disabled APN.
          */
        public static final String CARRIER_ENABLED = "carrier_enabled";

        /**
          * Radio Access Technology info
          * To check what values can hold, refer to ServiceState.java.
          * This should be spread to other technologies,
          * but currently only used for LTE(14) and EHRPD(13).
          */
        public static final String BEARER = "bearer";
    }

    /**
     * Contains received SMS cell broadcast messages.
     */
    public static final class CellBroadcasts implements BaseColumns {

        /** Not instantiable. */
        private CellBroadcasts() {}

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =
            Uri.parse("content://cellbroadcasts");

        /**
         * Message geographical scope.
         * <P>Type: INTEGER</P>
         */
        public static final String GEOGRAPHICAL_SCOPE = "geo_scope";

        /**
         * Message serial number.
         * <P>Type: INTEGER</P>
         */
        public static final String SERIAL_NUMBER = "serial_number";

        /**
         * PLMN of broadcast sender. (SERIAL_NUMBER + PLMN + LAC + CID) uniquely identifies a
         * broadcast for duplicate detection purposes.
         * <P>Type: TEXT</P>
         */
        public static final String PLMN = "plmn";

        /**
         * Location Area (GSM) or Service Area (UMTS) of broadcast sender. Unused for CDMA.
         * Only included if Geographical Scope of message is not PLMN wide (01).
         * <P>Type: INTEGER</P>
         */
        public static final String LAC = "lac";

        /**
         * Cell ID of message sender (GSM/UMTS). Unused for CDMA. Only included when the
         * Geographical Scope of message is cell wide (00 or 11).
         * <P>Type: INTEGER</P>
         */
        public static final String CID = "cid";

        /**
         * Message code (OBSOLETE: merged into SERIAL_NUMBER).
         * <P>Type: INTEGER</P>
         */
        public static final String V1_MESSAGE_CODE = "message_code";

        /**
         * Message identifier (OBSOLETE: renamed to SERVICE_CATEGORY).
         * <P>Type: INTEGER</P>
         */
        public static final String V1_MESSAGE_IDENTIFIER = "message_id";

        /**
         * Service category (GSM/UMTS message identifier, CDMA service category).
         * <P>Type: INTEGER</P>
         */
        public static final String SERVICE_CATEGORY = "service_category";

        /**
         * Message language code.
         * <P>Type: TEXT</P>
         */
        public static final String LANGUAGE_CODE = "language";

        /**
         * Message body.
         * <P>Type: TEXT</P>
         */
        public static final String MESSAGE_BODY = "body";

        /**
         * Message delivery time.
         * <P>Type: INTEGER (long)</P>
         */
        public static final String DELIVERY_TIME = "date";

        /**
         * Has the message been viewed?
         * <P>Type: INTEGER (boolean)</P>
         */
        public static final String MESSAGE_READ = "read";

        /**
         * Message format (3GPP or 3GPP2).
         * <P>Type: INTEGER</P>
         */
        public static final String MESSAGE_FORMAT = "format";

        /**
         * Message priority (including emergency).
         * <P>Type: INTEGER</P>
         */
        public static final String MESSAGE_PRIORITY = "priority";

        /**
         * ETWS warning type (ETWS alerts only).
         * <P>Type: INTEGER</P>
         */
        public static final String ETWS_WARNING_TYPE = "etws_warning_type";

        /**
         * CMAS message class (CMAS alerts only).
         * <P>Type: INTEGER</P>
         */
        public static final String CMAS_MESSAGE_CLASS = "cmas_message_class";

        /**
         * CMAS category (CMAS alerts only).
         * <P>Type: INTEGER</P>
         */
        public static final String CMAS_CATEGORY = "cmas_category";

        /**
         * CMAS response type (CMAS alerts only).
         * <P>Type: INTEGER</P>
         */
        public static final String CMAS_RESPONSE_TYPE = "cmas_response_type";

        /**
         * CMAS severity (CMAS alerts only).
         * <P>Type: INTEGER</P>
         */
        public static final String CMAS_SEVERITY = "cmas_severity";

        /**
         * CMAS urgency (CMAS alerts only).
         * <P>Type: INTEGER</P>
         */
        public static final String CMAS_URGENCY = "cmas_urgency";

        /**
         * CMAS certainty (CMAS alerts only).
         * <P>Type: INTEGER</P>
         */
        public static final String CMAS_CERTAINTY = "cmas_certainty";

        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = DELIVERY_TIME + " DESC";

        /**
         * Query columns for instantiating {@link android.telephony.CellBroadcastMessage} objects.
         */
        public static final String[] QUERY_COLUMNS = {
                _ID,
                GEOGRAPHICAL_SCOPE,
                PLMN,
                LAC,
                CID,
                SERIAL_NUMBER,
                SERVICE_CATEGORY,
                LANGUAGE_CODE,
                MESSAGE_BODY,
                DELIVERY_TIME,
                MESSAGE_READ,
                MESSAGE_FORMAT,
                MESSAGE_PRIORITY,
                ETWS_WARNING_TYPE,
                CMAS_MESSAGE_CLASS,
                CMAS_CATEGORY,
                CMAS_RESPONSE_TYPE,
                CMAS_SEVERITY,
                CMAS_URGENCY,
                CMAS_CERTAINTY
        };
    }

    public static final class Intents {
        private Intents() {
            // Not instantiable
        }

        /**
         * Broadcast Action: A "secret code" has been entered in the dialer. Secret codes are
         * of the form *#*#<code>#*#*. The intent will have the data URI:</p>
         *
         * <p><code>android_secret_code://&lt;code&gt;</code></p>
         */
        public static final String SECRET_CODE_ACTION =
                "android.provider.Telephony.SECRET_CODE";

        /**
         * Broadcast Action: The Service Provider string(s) have been updated.  Activities or
         * services that use these strings should update their display.
         * The intent will have the following extra values:</p>
         * <ul>
         *   <li><em>showPlmn</em> - Boolean that indicates whether the PLMN should be shown.</li>
         *   <li><em>plmn</em> - The operator name of the registered network, as a string.</li>
         *   <li><em>showSpn</em> - Boolean that indicates whether the SPN should be shown.</li>
         *   <li><em>spn</em> - The service provider name, as a string.</li>
         * </ul>
         * Note that <em>showPlmn</em> may indicate that <em>plmn</em> should be displayed, even
         * though the value for <em>plmn</em> is null.  This can happen, for example, if the phone
         * has not registered to a network yet.  In this case the receiver may substitute an
         * appropriate placeholder string (eg, "No service").
         *
         * It is recommended to display <em>plmn</em> before / above <em>spn</em> if
         * both are displayed.
         *
         * <p>Note this is a protected intent that can only be sent
         * by the system.
         */
        public static final String SPN_STRINGS_UPDATED_ACTION =
                "android.provider.Telephony.SPN_STRINGS_UPDATED";

        public static final String EXTRA_SHOW_PLMN  = "showPlmn";
        public static final String EXTRA_PLMN       = "plmn";
        public static final String EXTRA_SHOW_SPN   = "showSpn";
        public static final String EXTRA_SPN        = "spn";
    }
}
