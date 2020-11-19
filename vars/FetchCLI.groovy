def call(args){
       echo "Inside execute CLI"
       echo "Inside function file"
       def cli = 'python3 -m venv .;source ./bin/activate;pip install -r requirements.txt;ls -lt ;  python run.py --rhbuild ' + args['rhbuild'] + ' --global-conf ' + args['global_conf'] + ' --osp-cred ~/osp-cred-ci-2.yaml --inventory ' + args['inventory'] + ' --suite ' + args['suite_name'] + ' --log-level info --insecure-registry '
       echo "CLI defined"
       echo cli
       if(args['rhs_ceph_repo']?.trim()){
          cli = cli + ' --rhs-ceph-repo ' + args['rhs_ceph_repo'] + ' --ignore-latest-container'
       }
       if(args['add_repo']?.trim()){
          cli = cli + ' --add-repo ' + args['add_repo']
       }
       echo "CLI modified"
       echo cli
       return cli
}
