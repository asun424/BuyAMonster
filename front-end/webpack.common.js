module.exports = {
    entry: './client/index.tsx',
    resolve: {
      extensions: [".tsx", ".ts", ".js"]
    },
    context: __dirname,
    devtool: 'source-map',
    module: {
      rules: [
        {
          test: /\.(ts|js)x?$/,
          exclude: /node_modules/,
          use: 'ts-loader',
        },
        {
          test: /\.css$/i,
          use: ['style-loader', 'css-loader', 'postcss-loader'],
        },
      ],
    },
    output: {
      path: __dirname + '/public',
      filename: 'bundle.js',
    },
  };