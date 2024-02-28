using Unity.Collections;
using Unity.Collections.NotBurstCompatible;
using Unity.Mathematics;

namespace UnityEngine.U2D.Animation
{
    internal static class MeshUtilities
    {
        /// <summary>
        /// Get the outline edges from a set of indices.
        /// This method expects the index array to be laid out with one triangle for every 3 indices.
        /// E.g. triangle 0: index 0 - 2, triangle 1: index 3 - 5, etc.
        /// </summary>
        public static int2[] GetOutlineEdges(in int[] indices)
        {
            var edges = new NativeHashMap<int, int3>(indices.Length / 3, Allocator.Persistent);

            for (var i = 0; i < indices.Length; i += 3)
            {
                var i0 = indices[i];
                var i1 = indices[i + 1];
                var i2 = indices[i + 2];

                var edge0 = new int2(i0, i1);
                var edge1 = new int2(i1, i2);
                var edge2 = new int2(i2, i0);

                AddToEdgeMap(edge0, ref edges);
                AddToEdgeMap(edge1, ref edges);
                AddToEdgeMap(edge2, ref edges);
            }    

#if COLLECTIONS_2_0_OR_ABOVE
            var outlineEdges = new NativeList<int2>(edges.Count, Allocator.Temp);
#else
            var outlineEdges = new NativeList<int2>(edges.Count(), Allocator.Temp);
#endif
            foreach(var edgePair in edges)
            {
                // If an edge is only used in one triangle, it is an outline edge.
                if (edgePair.Value.z == 1)
                    outlineEdges.Add(edgePair.Value.xy);
            }
            edges.Dispose();

            return Extensions.ToArrayNBC(outlineEdges);
        }

        static void AddToEdgeMap(int2 edge, ref NativeHashMap<int, int3> edgeMap)
        {
            var tmpEdge = math.min(edge.x, edge.y) == edge.x ? edge.xy : edge.yx;
            var hashCode = tmpEdge.GetHashCode();
            
            // We store the hashCode as key, so that we can do less GetHashCode-calls.
            // Then we store the count the int3s z-value.
            if (!edgeMap.ContainsKey(hashCode))
                edgeMap.Add(hashCode, new int3(edge, 1));
            else
            {
                var val = edgeMap[hashCode];
                val.z++;
                edgeMap[hashCode] = val;
            }
        }
    }
}