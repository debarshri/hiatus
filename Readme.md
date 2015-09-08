### Hiatus

Inspired by Stripe's [MoSQL](https://github.com/debarshri/hiatus.git), this is our implementation of hbase -> oracle streaming.


#### Initial specs
 
 - Fast refresh 
  * Involves refresh the target tables every 'x' minutes. Current implementation has x = 15 minutes based on our specs.
  
 - ASAP refresh
  * Refreshes retrospectively.
  
 - Bulk refresh
  * Refreshes data between the tables every 'y' hours. Current implementation y = 2 hours.
  
 - Full/Complete refresh
  * Refreshes table completely.

#### Configuration
 
 ```
 [
      {
       "conf1" : {
                  "source" : "hbase_table",
                  "target"  : "oracle_table",
                  "mapping" : {
                                 "hbase.col1":"ora.col1"
                              },
                  "sync" : "asap",
                  "upsert" : true
              }
       }
 ]
 ```
 
#### Example (Proposed specs)
 
##### Submitting configurations
 ```
 hiatus --conf=conf/table.hiatus --sql=<connection-string> --hbase=<zookeeper-quorom1>,<zookeeper-quorom2>,<zookeeper-quorom3>
 ```

##### Server options

 ```
 hiatus-server --server_restart
 
 hiatus-server --server_start
 
 hiatus-server --server_stop
 ```

##### Administration
 
  ```
  hiatus-admin --list

  hiatus-admin --kill=<task-id>
  
  hiatus-admin --delete=<job-id>

  hiatus-admin --executing
  
  hiatus-admin --execute=<job-id>
  ```