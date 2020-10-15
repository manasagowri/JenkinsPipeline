def exeuteCLI(args){
   echo "Inside execute CLI"
   checkout([$class: 'GitSCM', branches: [[name: args['git_branch']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/' + args['github_repo_link']]]])
            def cli = 'python3 -m venv .;source ./bin/activate;pip install -r requirements.txt;ls -lt ;  python run.py --rhbuild $rhbuild   --global-conf $global_conf --osp-cred ~/osp-cred-ci-2.yaml --inventory $inventory  --suite $suite_name --log-level info --ignore-latest-container --insecure-registry --post-results  --report-portal '.format(args['rhbuild'], args['global_conf'], args['inventory'], args['suite_name'])
            if(args['rhs-ceph-repo']?.trim()){
               cli = cli + '--rhs-ceph-repo $rhs_ceph_repo'.format(args['rhs_ceph_repo']
            }
            sh cli
}
