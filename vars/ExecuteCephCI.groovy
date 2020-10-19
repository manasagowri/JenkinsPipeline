def call(args){
       echo "Inside execute CLI"
       echo "Inside function file"
       checkout([$class: 'GitSCM', branches: [[name: args['git_branch']]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/' + args['github_repo_link']]]])
       echo "Check out complete"
       def cli = 'python3 -m venv .;source ./bin/activate;pip install -r requirements.txt;ls -lt ;  python run.py --rhbuild ' + args['rhbuild'] + ' --global-conf ' + args['global_conf'] + ' --osp-cred ~/osp-cred-ci-2.yaml --inventory ' + args['inventory'] + ' --suite ' + args['suite_name'] + ' --log-level info --ignore-latest-container --insecure-registry '
       echo "CLI defined"
       echo cli
       if(args['rhs-ceph-repo']?.trim()){
          cli = cli + '--rhs-ceph-repo ' + args['rhs_ceph_repo']
       }
       echo "CLI modified"
       echo cli
       sh cli
}
