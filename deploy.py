# -*- coding: utf-8 -*-
from hdeploy import Job, Package, Container

job = Job(
    # Job名称
    job_name='zzw-flink-demo',
    # 所属gateway
    gateway_group='default',
    #目标集群
    cluster='daily',
    #安装路径
    root_dir='/home/admin/will.zzw/zzw-flink-demo',
    #install
    shell_install='',
    #启动脚本
    shell_start='bin/start.sh',
    #停止脚本
    shell_stop='bin/stop.sh',
    #卸载脚本
    shell_uninstall='',
    #desc
    publish_desc='blink demo from zzw.hq',
    #containers
    containers=[
        Container(
            #安装相对路径
            install_dir='',
            packages=[
                Package(
                    #安装包名称
                    name='zzw-flink-demo',
                    #包版本
                    version='zzw-flink-demo-0.0.6-0')
            ]
        )
    ]
)